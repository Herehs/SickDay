package com.example.up.domain.repository

import com.example.up.common.Resource
import com.example.up.domain.model.KpData
import kotlinx.coroutines.flow.Flow

interface KpRepository {
    suspend fun getKpData(): Flow<Resource<List<KpData>>>
}