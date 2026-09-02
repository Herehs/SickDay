package com.example.server.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    val hourly: Hourly,
)
@Serializable
data class Hourly(
    val time: List<String>,
    val temperature_2m: List<Float>,
    val relative_humidity_2m: List<Float>,
    val pressure_msl: List<Float>
)