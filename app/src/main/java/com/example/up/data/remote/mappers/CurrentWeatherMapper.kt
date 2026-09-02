package com.example.up.data.remote.mappers

import com.example.server.data.remote.dto.WeatherResponseDto
import com.example.up.domain.model.Weather

fun WeatherResponseDto.toDomain() = Weather(
    humidity = current.humidity,
    pressure = current.pressure,
    temperature = current.temperature
)