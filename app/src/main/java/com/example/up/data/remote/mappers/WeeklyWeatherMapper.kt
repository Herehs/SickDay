package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.WeeklyWeatherDto
import com.example.up.domain.model.WeeklyWeather

fun WeeklyWeatherDto.toDomain(): List<WeeklyWeather> {
    return daily.time.indices.map { i ->
        WeeklyWeather(
            date = daily.time[i],
            temperature = daily.temperature_2m_mean[i],
            humidity = daily.relative_humidity_2m_mean[i]
        )
    }
}