package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Position
import com.example.up.domain.repository.PositionRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentPositionUseCase(
    private val repository: PositionRepository
) {
    suspend operator fun invoke(): Flow<Resource<Position>> {
        return repository.getCurrentPosition()
    }
}