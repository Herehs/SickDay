package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val humidity: Float,
    val kp_index: Float,
    val pressure: Float,
    val temperature: Float
)