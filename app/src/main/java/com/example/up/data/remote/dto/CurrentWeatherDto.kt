package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDto(
    val humidity: Int,
    val kp_index: Float,
    val pressure: Int,
    val temperature: Float
)