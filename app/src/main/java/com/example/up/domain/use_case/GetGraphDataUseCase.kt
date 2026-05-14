package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.GraphData
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetGraphDataUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float,
        date: String
    ): Flow<Resource<GraphData>>{
        return repository.getGraphData(
            lat = lat,
            lon = lon,
            date = date
        )
    }
}