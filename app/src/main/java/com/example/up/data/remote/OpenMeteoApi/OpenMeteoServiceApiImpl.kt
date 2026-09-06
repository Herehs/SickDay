package com.example.up.data.remote.OpenMeteoApi

import HttpRoutes.BASE_URL
import com.example.server.data.remote.dto.HourlyWeatherDto
import com.example.server.data.remote.dto.WeatherResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class OpenMeteoServiceApiImpl(
    private val client: HttpClient
) : OpenMeteoServiceApi {
    override suspend fun getCurrentWeather(
        lat: Double,
        lon: Double
    ): WeatherResponseDto = client.get(BASE_URL){
        parameter("latitude", lat)
        parameter("longitude", lon)
        parameter("current", "temperature_2m,relative_humidity_2m,pressure_msl")
    }.body()

    override suspend fun getHourlyWeather(
        lat: Double,
        lon: Double,
        date: String
    ): HourlyWeatherDto = client.get(BASE_URL){
        parameter("latitude", lat)
        parameter("longitude", lon)
        parameter("hourly", "pressure_msl,temperature_2m,relative_humidity_2m")
        parameter("start_date", date)
        parameter("end_date", date)
    }.body()
}