package com.example.up.domain.use_case

import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.repository.WeatherRepository

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float
    ): CurrentWeather{
        return repository.getCurrentWeather(lat = lat, lon = lon)
    }
}