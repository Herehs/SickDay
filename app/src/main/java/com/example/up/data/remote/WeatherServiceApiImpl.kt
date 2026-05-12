package com.example.up.data.remote

import com.example.up.data.remote.dto.CurrentWeatherDto
import io.ktor.client.HttpClient

class WeatherServiceImpl(
    private val client: HttpClient
) : WeatherService {
    override suspend fun getCurrentWeather(): List<CurrentWeatherDto> {
        TODO("Not yet implemented")
    }
}