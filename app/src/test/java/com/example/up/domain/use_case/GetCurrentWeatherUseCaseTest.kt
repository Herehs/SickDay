package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.KpData
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
import java.time.LocalDate

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
        val weatherFlow = flowOf(Resource.Success(weather))

        val kpData = listOf(
            KpData(
                Kp = 1.0,
                a_running = 1,
                station_count = 1,
                time = LocalDate.of(2025, 1, 1)
            )
        )
        val kpDateFlow = flowOf(Resource.Success(kpData))


        coEvery {
            weatherRepository.getCurrentWeather(
                lat = 50.0,
                lon = 30.0
            )
        } returns weatherFlow

        coEvery {
            kpRepository.getKpData()
        } returns kpDateFlow


        val result = useCase(
            lat = 50.0,
            lon = 30.0
        ).first()

        assertTrue(result is Resource.Success)

        coVerify {
            weatherRepository.getCurrentWeather(
                lat = 50.0,
                lon = 30.0
            )
        }
        coVerify {
            kpRepository.getKpData()
        } 

    }
}