package com.example.up.domain.model

data class CurrentWeather(
    val humidity: Int,
    val kp_index: Float,
    val pressure: Int,
    val temperature: Float
)
