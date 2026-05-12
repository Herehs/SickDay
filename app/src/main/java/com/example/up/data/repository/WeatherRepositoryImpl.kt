package com.example.up.data.repository

import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.mappers.toDomain
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.repository.WeatherRepository

class WeatherRepositoryImpl(
    val api: WeatherServiceApi
) : WeatherRepository{
    override suspend fun getCurrentWeather(
        lat: Float,
        lon: Float
    ): CurrentWeather {
        return api.getCurrentWeather(lat = lat, lon = lon).toDomain()
    }
}