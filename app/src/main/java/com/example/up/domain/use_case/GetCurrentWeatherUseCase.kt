package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Weather
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float
    ): Flow<Resource<Weather>> {
        return repository.getCurrentWeather(lat = lat, lon = lon)
    }
}