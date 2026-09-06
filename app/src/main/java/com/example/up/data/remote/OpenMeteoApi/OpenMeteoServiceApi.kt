package com.example.up.data.remote.OpenMeteoApi

import com.example.server.data.remote.dto.HourlyWeatherDto
import com.example.server.data.remote.dto.WeatherResponseDto

interface OpenMeteoServiceApi {
    suspend fun getCurrentWeather(lat: Double, lon: Double): WeatherResponseDto
    suspend fun getHourlyWeather(lat: Double, lon: Double, date: String): HourlyWeatherDto
}