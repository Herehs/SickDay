package com.example.up.data.remote.dto

data class AverageWeather(
    val humidity_avg: Int,
    val kp_index_avg: Double,
    val pressure_avg: Int,
    val temperature_avg: Double
)