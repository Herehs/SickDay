package com.example.up.data.repository

import com.example.up.common.Resource
import com.example.up.data.local.cache.CacheService
import com.example.up.data.local.cache.dto.PositionCache
import com.example.up.data.local.location.LocationProvider
import com.example.up.data.local.mappers.toDomain
import com.example.up.domain.model.Position
import com.example.up.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class PositionRepositoryImpl(
    private val positionProvider: LocationProvider,
    private val positionCache: CacheService<PositionCache>
) : PositionRepository {

    private var inMemoryCache: PositionCache? = null
    private val cacheTtlMillis: Long = 1000 * 60 * 60L

    override suspend fun getCurrentPosition(): Flow<Resource<Position>> = flow {
        val isExpired = System.currentTimeMillis() - (inMemoryCache?.savedAtMs ?: 0L) > cacheTtlMillis

        try {
            emit(Resource.Loading())
            val cached = inMemoryCache ?: positionCache.load().first()

            if(cached != null || !isExpired){
                inMemoryCache = cached
                emit(Resource.Success(cached.toDomain()))
                return@flow
            }
            val fresh = positionProvider.getCurrentLocation()
            if(fresh == null){
                emit(Resource.Error(message = "Location unavailable", data = null))
                return@flow
            }
            val cache = PositionCache(
                position = Position(fresh.latitude, lon = fresh.longitude),
                savedAtMs = System.currentTimeMillis()
            )

            positionCache.save(cache)
            inMemoryCache = cache

            emit(Resource.Success(cache.toDomain()))
        }
        catch (e: Exception){
            emit(Resource.Error(e.message ?: "unexpected error"))
        }
    }
}