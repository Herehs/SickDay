package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Weather
import com.example.up.domain.repository.KpRepository
import com.example.up.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCurrentWeatherUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val kpRepository = mockk<KpRepository>()
    private lateinit var useCase: GetCurrentWeatherUseCase

    @BeforeEach
    fun setup() {
        useCase = GetCurrentWeatherUseCase(
            weatherRepository,
            kpRepository
        )
    }

    @Test
    fun `should return current weather`() = runTest {

        val weather = Weather(
            temperature = 20f
        )

        val flow = flowOf(Resource.Success(weather))

        coEvery {
            weatherRepository.getCurrentWeather(
                lat = 50f,
                lon = 30f
            )
        } returns flow

        val result = useCase(
            lat = 50f,
            lon = 30f
        ).first()

        assertTrue(result is Resource.Success)

        coVerify {
            weatherRepository.getCurrentWeather(
                lat = 50f,
                lon = 30f
            )
        }
    }
}