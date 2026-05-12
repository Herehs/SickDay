package com.example.up.domain.repository

import com.example.up.domain.model.CurrentWeather

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Float, lon: Float) : CurrentWeather
}