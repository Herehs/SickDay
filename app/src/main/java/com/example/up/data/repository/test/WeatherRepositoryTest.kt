package com.example.up.data.repository.test

import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.dto.CurrentWeatherDto
import com.example.up.data.remote.mappers.toDomain
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.repository.WeatherRepository

class WeatherRepositoryTest(
    val api: WeatherServiceApi
) : WeatherRepository{
    override suspend fun getCurrentWeather(
        lat: Float,
        lon: Float
    ): CurrentWeather {
        return CurrentWeatherDto(
            humidity = 40,
            temperature = 23f,
            kp_index = 9f,
            pressure = 777
        ).toDomain()
    }
}