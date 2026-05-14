package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.CurrentWeatherDto
import com.example.up.domain.model.CurrentWeather

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    return CurrentWeather(
        humidity = humidity,
        kp_index = kp_index,
        pressure = pressure / 1.33f,
        temperature = temperature
    )
}