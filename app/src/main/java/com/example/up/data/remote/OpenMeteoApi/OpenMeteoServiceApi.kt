package com.example.up.data.remote.OpenMeteoApi

import com.example.server.data.remote.dto.HourlyWeatherDto
import com.example.server.data.remote.dto.WeatherResponseDto
import com.example.up.data.remote.dto.WeeklyWeatherDto
import com.example.up.domain.model.WeeklyWeather

interface OpenMeteoServiceApi {
    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResponseDto
    suspend fun getHourlyWeather(lat: Double, lon: Double, date: String): HourlyWeatherDto

    suspend fun getWeeklyWeather(
        lat: Double,
        lon: Double,
        startDate: String,
        endDate: String
    ): WeeklyWeatherDto
}