package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.AverageWeather
import com.example.up.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAvgWeatherUseCaseTest {

    private val repository = mockk<WeatherRepository>()

    private lateinit var useCase: GetAvgWeatherUseCase

    @Before
    fun setup() {
        useCase = GetAvgWeatherUseCase(repository)
    }

    @Test
    fun `invoke should return average weather`() = runTest {

        val weather = AverageWeather(
            temperature = 20f
        )

        val flow = flowOf(
            Resource.Success(weather)
        )

        coEvery {
            repository.getAvgWeather(
                lat = 50f,
                lon = 30f,
                date = "2025-05-14"
            )
        } returns flow

        val result = useCase(
            lat = 50f,
            lon = 30f,
            date = "2025-05-14"
        ).first()

        assert(result is Resource.Success)

        assertEquals(
            weather,
            result.data
        )

        coVerify {
            repository.getAvgWeather(
                lat = 50f,
                lon = 30f,
                date = "2025-05-14"
            )
        }
    }
}