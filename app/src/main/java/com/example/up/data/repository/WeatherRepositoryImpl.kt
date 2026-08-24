package com.example.up.data.repository

import com.example.up.common.Resource
import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.mappers.toDomain
import com.example.up.data.remote.safeApiCall
import com.example.up.domain.model.AverageWeather
import com.example.up.domain.model.Calendar
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.model.GraphData
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class WeatherRepositoryImpl(
    val api: WeatherServiceApi
) : WeatherRepository{
    override suspend fun getCalendar(
        lat: Float, lon: Float, year: Int, month: Int
    ): Flow<Resource<Calendar>> = safeApiCall {
        api.getCalendar(lat = lat, lon = lon, year = year, month = month).toDomain()
    }

    override suspend fun getCurrentWeather(
        lat: Float, lon: Float
    ): Flow<Resource<CurrentWeather>> = safeApiCall {
        api.getCurrentWeather(lat = lat, lon = lon).toDomain()
    }

    override suspend fun getAvgWeather(
        lat: Float, lon: Float, date: String
    ): Flow<Resource<AverageWeather>> = safeApiCall {
        api.getAvgWeather(lat = lat, lon = lon, date = date).toDomain()
    }

    override suspend fun getGraphData(
        lat: Float, lon: Float, date: String
    ): Flow<Resource<GraphData>> = safeApiCall {
        api.getGraphData(lat = lat, lon = lon, date = date).toDomain()
    }
}