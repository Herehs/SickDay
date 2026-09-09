package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.WeeklyWeather
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class GetWeeklyWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(
        lat: Double,
        lon: Double,
        currentDate: String
    ): Flow<Resource<List<WeeklyWeather>>> {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val currentDate = LocalDate.parse(currentDate)
        val startDate = currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endDate = currentDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

        return weatherRepository.getWeeklyWeather(
            lat = lat,
            lon = lon,
            startDate = startDate.format(formatter),
            endDate = endDate.format(formatter)
        )
    }
}