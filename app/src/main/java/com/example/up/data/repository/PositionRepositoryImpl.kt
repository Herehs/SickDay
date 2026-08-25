package com.example.up.data.repository

import com.example.up.common.Resource
import com.example.up.data.local.location.LocationProvider
import com.example.up.data.local.mappers.toDomain
import com.example.up.domain.model.Position
import com.example.up.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PositionRepositoryImpl(
    private val positionProvider: LocationProvider
) : PositionRepository {

    private var cache: Position? = null

    override suspend fun getCurrentPosition(): Flow<Resource<Position>> = flow {
        try {
            emit(Resource.Loading())

            cache?.let {
                emit(Resource.Success(it))
                return@flow
            }

            val position = positionProvider.getCurrentLocation()?.toDomain()
            cache = position

            if (position != null) {
                emit(Resource.Success(position))
            } else {
                emit(Resource.Error("Location unavailable"))
            }
        }
        catch (e: Exception){
            emit(Resource.Error(e.message ?: "unexpected error"))
        }
    }
}