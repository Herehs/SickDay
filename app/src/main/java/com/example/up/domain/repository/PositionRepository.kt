package com.example.up.domain.repository

import android.location.Location
import com.example.up.common.Resource
import com.example.up.domain.model.Position
import kotlinx.coroutines.flow.Flow

interface PositionRepository {
    suspend fun getCurrentPosition(): Flow<Resource<Position>>
}