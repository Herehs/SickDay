package com.example.up.data.repository

import android.util.Log
import com.example.up.common.Resource
import com.example.up.common.safeApiCall
import com.example.up.data.remote.SWPCApi.SWPCServiceApi
import com.example.up.data.remote.mappers.toDomain
import com.example.up.domain.model.KpData
import com.example.up.domain.repository.KpRepository
import kotlinx.coroutines.flow.Flow

class KpRepositoryImpl(
    private val api: SWPCServiceApi
) : KpRepository {
    override suspend fun getKpData(): Flow<Resource<List<KpData>>> = safeApiCall {
        Log.d(
            "Kp",
            "Kp repository was used"
        )
        api.getKRData().toDomain()
    }
}
