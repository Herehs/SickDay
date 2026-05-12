package com.example.up.data.remote

import com.example.up.data.remote.HttpRoutes.CURRENT_WEATHER
import com.example.up.data.remote.dto.CurrentWeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherServiceApiImpl(
    private val client: HttpClient
) : WeatherServiceApi {
    override suspend fun getCurrentWeather(
        lat: Float,
        lon: Float
    ): CurrentWeatherDto {
        return client.get(CURRENT_WEATHER){
            parameter("lat", lat)
            parameter("lon", lon)
        }.body()
    }
}