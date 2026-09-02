package com.example.server.data.remote

import com.example.server.data.remote.dto.HourlyWeatherDto
import com.example.server.data.remote.dto.WeatherResponseDto

interface OpenMeteoServiceApi {
    suspend fun getCurrentWeather(lat: Float, lon: Float): WeatherResponseDto
    suspend fun getHourlyWeather(lat: Float, lon: Float, date: String): HourlyWeatherDto
}