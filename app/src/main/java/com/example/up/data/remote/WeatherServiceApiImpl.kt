package com.example.up.data.remote

import android.util.Log
import com.example.up.data.remote.HttpRoutes.AVG
import com.example.up.data.remote.HttpRoutes.CALENDAR
import com.example.up.data.remote.HttpRoutes.CURRENT_WEATHER
import com.example.up.data.remote.HttpRoutes.GRAPH
import com.example.up.data.remote.dto.AverageWeatherDto
import com.example.up.data.remote.dto.CalendarDto
import com.example.up.data.remote.dto.CurrentWeatherDto
import com.example.up.data.remote.dto.GraphDtoItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class WeatherServiceApiImpl(
    private val client: HttpClient
) : WeatherServiceApi {


    override suspend fun getCurrentWeather(
        lat: Float,
        lon: Float
    ): CurrentWeatherDto {
        return client.get(CURRENT_WEATHER){
            parameter("lat", lat)
            parameter("lon", lon)
        }.body()
    }

    override suspend fun getCalendar(
        lat: Float,
        lon: Float,
        year: Int,
        month: Int
    ): CalendarDto {
        Log.e("test", "WeatherServiceApiImpl")
        return client.get(CALENDAR){
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("year", year)
            parameter("month", month)
        }.body()
    }

    override suspend fun getAvgWeather(
        lat: Float,
        lon: Float,
        date: String
    ): AverageWeatherDto {
        return client.get(AVG){
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("date", date)
        }.body()
    }

    override suspend fun getGraphData(
        lat: Float,
        lon: Float,
        date: String
    ): List<GraphDtoItem> {
        return client.get(GRAPH){
            parameter("lat", lat)
            parameter("lon", lon)
            parameter("date", date)
        }.body()
    }
}