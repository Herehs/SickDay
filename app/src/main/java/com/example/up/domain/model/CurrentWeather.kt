package com.example.up.domain.model

data class WeatherInfoState(
    val temperature: Int = 0,
    val pressure: Float = 0f,
    val humidity: Float = 0f,
    val kp_index: Float = 0f
)
