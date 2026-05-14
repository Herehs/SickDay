package com.example.up.data.repository

import android.util.Log
import com.example.up.common.Resource
import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.mappers.toDomain
import com.example.up.domain.model.AverageWeather
import com.example.up.domain.model.Calendar
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.model.GraphData
import com.example.up.domain.repository.WeatherRepository
import io.ktor.client.plugins.ResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.io.EOFException

class WeatherRepositoryImpl(
    val api: WeatherServiceApi
) : WeatherRepository{
    override suspend fun getCalendar(
        lat: Float,
        lon: Float,
        year: Int,
        month: Int
    ): Flow<Resource<Calendar>> = flow {
        Log.e("test", "WeatherRepositoryImpl", )
        try {
            emit(Resource.Loading())
            val calendar = api.getCalendar(lat = lat, lon = lon, year = year, month = month).toDomain()
            emit(Resource.Success(calendar))
        }
        catch (e: ResponseException){
            emit(Resource.Error(message = e.message ?: "Unexpected error"))
        }
        catch (e: EOFException){
            emit(Resource.Error(message = "Сервер закрыл соединение: ${e.message}"))
        }
    }

    override suspend fun getCurrentWeather(
        lat: Float,
        lon: Float
    ): Flow<Resource<CurrentWeather>> = flow {
        try {
            emit(Resource.Loading())
            val weather = api.getCurrentWeather(lat = lat, lon = lon).toDomain()
            emit(Resource.Success(data = weather))
        }
        catch (e: ResponseException){
            Resource.Error<String>(message = e.message ?: "Unexpected error")
        }
    }

    override suspend fun getAvgWeather(
        lat: Float,
        lon: Float,
        date: String
    ): Flow<Resource<AverageWeather>> = flow {
        try {
            emit(Resource.Loading())
            val weather = api.getAvgWeather(lat = lat, lon = lon, date = date).toDomain()
            emit(Resource.Success(data = weather))
        }
        catch (e: ResponseException){
            Resource.Error<String>(message = e.message ?: "Unexpected error")
        }
    }


    override suspend fun getGraphData(
        lat: Float,
        lon: Float,
        date: String
    ): Flow<Resource<GraphData>> = flow {
        try {
            emit(Resource.Loading())
            val graphData = api.getGraphData(lat = lat, lon = lon, date = date).toDomain()
            emit(Resource.Success(data = graphData))
        }
        catch (e: ResponseException){
            Resource.Error<String>(message = e.message ?: "Unexpected error")
        }
    }
}