package com.example.up.domain.repository

import com.example.up.common.Resource
import com.example.up.domain.model.HourlyWeather
import com.example.up.domain.model.Weather
import kotlinx.coroutines.flow.Flow


interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Float, lon: Float): Flow<Resource<Weather>>
    suspend fun getAvgWeather(lat: Float, lon: Float, date: String): Flow<Resource<Weather>>
}