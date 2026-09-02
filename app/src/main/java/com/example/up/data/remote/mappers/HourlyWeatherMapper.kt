package com.example.up.data.remote.mappers

import com.example.server.data.remote.dto.HourlyWeatherDto
import com.example.up.common.avg
import com.example.up.domain.model.Weather

fun HourlyWeatherDto.toDomain(): Weather = Weather(
    temperature = hourly.temperature_2m.avg(),
    humidity = hourly.relative_humidity_2m.avg(),
    pressure = hourly.pressure_msl.avg()
)
