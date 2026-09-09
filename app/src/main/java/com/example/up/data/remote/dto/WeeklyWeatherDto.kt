package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class WeeklyWeatherDto(
    val daily: Daily
)

@Serializable
data class Daily(
    val time: List<String>,
    val  temperature_2m_mean: List<Double>,
    val relative_humidity_2m_mean: List<Double>
)
