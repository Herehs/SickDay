package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.CurrentWeather
import com.example.up.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCurrentWeatherUseCaseTest {

    private val repository = mockk<WeatherRepository>()
    private lateinit var useCase: GetCurrentWeatherUseCase

    @Before
    fun setup() {
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `should return current weather`() = runTest {

        val weather = CurrentWeather(
            temperature = 20f
        )

        val flow = flowOf(Resource.Success(weather))

        coEvery {
            repository.getCurrentWeather(
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
            repository.getCurrentWeather(
                lat = 50f,
                lon = 30f
            )
        }
    }
}