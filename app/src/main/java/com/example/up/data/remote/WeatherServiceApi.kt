package com.example.up.data.remote

import com.example.up.data.remote.dto.AverageWeatherDto
import com.example.up.data.remote.dto.CalendarDto
import com.example.up.data.remote.dto.CurrentWeatherDto
import com.example.up.data.remote.dto.GraphDtoItem


interface WeatherServiceApi {
    suspend fun getCalendar(lat: Float, lon: Float, year: Int, month: Int): CalendarDto
    suspend fun getCurrentWeather(lat: Float, lon: Float): CurrentWeatherDto
    suspend fun getAvgWeather(lat: Float, lon: Float, date: String): AverageWeatherDto
    suspend fun getGraphData(lat: Float, lon: Float, date: String): List<GraphDtoItem>
}
