package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Calendar
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow

class GetCalendarUseCase(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float,
        year: Int,
        month: Int
    ): Flow<Resource<Calendar>>{
        return repository.getCalendar(
            lat = lat,
            lon = lon,
            year = year,
            month = month
        )
    }
}