package com.example.up.domain.model

data class HourlyWeather(
    val time: List<String>,
    val temperature: List<Float>,
    val humidity: List<Float>,
    val pressure: List<Float>
)
