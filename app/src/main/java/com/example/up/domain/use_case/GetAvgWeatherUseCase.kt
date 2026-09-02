package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.HourlyWeather
import com.example.up.domain.model.Weather
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetAvgWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float,
        date: String
    ): Flow<Resource<Weather>>{
        return repository.getAvgWeather(
            lat = lat,
            lon = lon,
            date = date
        )
    }
}