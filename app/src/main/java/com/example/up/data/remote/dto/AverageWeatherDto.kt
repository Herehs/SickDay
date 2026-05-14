package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class AverageWeatherDto(
    val humidity_avg: Float,
    val kp_index_avg: Float,
    val pressure_avg: Float,
    val temperature_avg: Float
)