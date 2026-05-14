package com.example.up.domain.repository

import com.example.up.common.Resource
import com.example.up.data.remote.dto.CalendarDto
import com.example.up.domain.model.AverageWeather
import com.example.up.domain.model.Calendar
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.model.GraphData
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(lat: Float, lon: Float): Flow<Resource<CurrentWeather>>

    suspend fun getCalendar(lat: Float, lon: Float, year: Int, month: Int): Flow<Resource<Calendar>>

    suspend fun getAvgWeather(lat: Float, lon: Float, date: String): Flow<Resource<AverageWeather>>

    suspend fun getGraphData(lat: Float, lon: Float, date: String): Flow<Resource<GraphData>>
}