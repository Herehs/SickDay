package com.example.up.domain.use_case

import com.example.up.domain.model.Position
import com.example.up.domain.repository.PositionRepository

class GetCurrentPositionUseCase(
    private val repository: PositionRepository
) {
    suspend operator fun invoke(): Position {
        val position = repository.getCurrentPosition()
        return Position(
            lat = position.latitude.toFloat(),
            lon = position.longitude.toFloat()
        )
    }
}