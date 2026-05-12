package com.example.up.data.remote

import com.example.up.data.remote.dto.CurrentWeatherDto


interface WeatherService {
    suspend fun getCurrentWeather(): List<CurrentWeatherDto>
}