package com.example.up.data.remote.dto

data class CurrentWeatherDto(
    val humidity: Int,
    val kp_index: Double,
    val pressure: Int,
    val temperature: Double
)