package com.example.up.domain.repository

import com.example.up.common.Resource
import com.example.up.domain.model.HourlyWeather
import com.example.up.domain.model.Weather
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Double, lon: Double): Flow<Resource<Weather>>
    suspend fun getAvgWeather(lat: Double, lon: Double, date: String): Flow<Resource<Weather>>
}