package com.example.up.data.repository

import com.example.server.data.remote.OpenMeteoServiceApi
import com.example.up.common.Resource
import com.example.up.data.remote.mappers.toDomain
import com.example.up.data.remote.safeApiCall
import com.example.up.domain.model.HourlyWeather
import com.example.up.domain.model.Weather
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    val api: OpenMeteoServiceApi
) : WeatherRepository{
    override suspend fun getCurrentWeather(lat: Float, lon: Float): Flow<Resource<Weather>> = safeApiCall {
        api.getCurrentWeather(lat = lat, lon = lon).toDomain()
    }
    override suspend fun getAvgWeather(lat: Float, lon: Float, date: String): Flow<Resource<Weather>> = safeApiCall {
        api.getHourlyWeather(
            lat = lat,
            lon = lon,
            date = date
        ).toDomain()
    }
}

