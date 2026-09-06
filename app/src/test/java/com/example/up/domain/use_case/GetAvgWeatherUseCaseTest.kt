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
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate
import kotlin.test.assertEquals

class GetAvgWeatherUseCaseTest {

    private val weatherRepository = mockk<WeatherRepository>()
    private val kpRepository = mockk<KpRepository>()

    private lateinit var useCase: GetAvgWeatherUseCase

    @BeforeEach
    fun setup() {
        useCase = GetAvgWeatherUseCase(
            repository = weatherRepository,
            kpRepository = kpRepository
        )
    }

    @Test
    fun `invoke should return average weather`() = runTest {

        val weather = Weather(
            temperature = 20f
        )

        val weatherFlow = flowOf(
            Resource.Success(weather)
        )

        val kpFlow = flowOf(
            Resource.Success(listOf(KpData(
                Kp = .1,
                a_running = 1,
                station_count = 8,
                time = LocalDate.of(2025, 5,14)
            )))
        )

        coEvery {
            weatherRepository.getAvgWeather(
                lat = 50f,
                lon = 30f,
                date = "2025-05-14"
            )
        } returns weatherFlow

        coEvery {
            kpRepository.getKpData()
        } returns kpFlow


        val result = useCase(
            lat = 50f,
            lon = 30f,
            date = "2025-05-14"
        ).first()

        assert(result is Resource.Success)

        val expected = weather.copy(kp_index = 0.1f)

        assertEquals(
            expected,
            result.data
        )

        coVerify {
            weatherRepository.getAvgWeather(
                lat = 50f,
                lon = 30f,
                date = "2025-05-14"
            )
        }
        coVerify {
            kpRepository.getKpData()
        }
    }
}