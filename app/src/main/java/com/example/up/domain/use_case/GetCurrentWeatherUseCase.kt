package com.example.up.domain.use_case

import android.util.Log
import com.example.up.common.Resource
import com.example.up.common.averageForDate
import com.example.up.domain.model.Weather
import com.example.up.domain.repository.KpRepository
import com.example.up.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import java.time.LocalDate

class GetCurrentWeatherUseCase(
    private val repository: WeatherRepository,
    private val kpRepository: KpRepository
) {
    suspend operator fun invoke(
        lat: Float,
        lon: Float
    ): Flow<Resource<Weather>> {
        return combine(
            repository.getCurrentWeather(lat = lat, lon = lon),
            kpRepository.getKpData()
        ) { weatherResource, kpResource ->
            when (weatherResource) {
                is Resource.Success -> {
                    val weather = weatherResource.data
                    if (weather == null) {
                        Resource.Error("Weather data is null")
                    } else {
                        val avgKp = (kpResource as? Resource.Success)
                            ?.data
                            ?.averageForDate(LocalDate.now())
                            ?.toFloat()
                            ?: 0f

                        Log.d(
                            "Kp",
                            "$avgKp"
                        )

                        Resource.Success(weather.copy(kp_index = avgKp))
                    }
                }
                is Resource.Error -> Resource.Error(weatherResource.message ?: "", null)
                is Resource.Loading -> Resource.Loading()
            }
        }
    }
}