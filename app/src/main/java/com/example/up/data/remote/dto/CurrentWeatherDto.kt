package com.example.server.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponseDto(
    val latitude: Double,
    val longitude: Double,
    val current: CurrentWeatherDto
)

@Serializable
data class CurrentWeatherDto(
    @SerialName("temperature_2m") val temperature: Float = 0.0f,
    @SerialName("relative_humidity_2m") val humidity: Float = 0.0f,
    @SerialName("pressure_msl") val pressure: Float = 0.0f
)