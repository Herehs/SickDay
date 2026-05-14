package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.AverageWeatherDto
import com.example.up.domain.model.AverageWeather

fun AverageWeatherDto.toDomain(): AverageWeather {
    return AverageWeather(
        humidity = humidity_avg,
        temperature = temperature_avg,
        kp_index = kp_index_avg,
        pressure = pressure_avg / 1.33f
    )
}