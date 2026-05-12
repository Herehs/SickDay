package com.example.up.data.remote

import com.example.up.data.remote.dto.CurrentWeatherDto


interface WeatherServiceApi {
    suspend fun getCurrentWeather(lat: Float, lon: Float): CurrentWeatherDto
}