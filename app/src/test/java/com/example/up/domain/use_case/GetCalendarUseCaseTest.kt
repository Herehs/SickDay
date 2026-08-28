package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Calendar
import com.example.up.domain.model.CalendarItem
import com.example.up.domain.repository.WeatherRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetCalendarUseCaseTest {

    private val repository = mockk<WeatherRepository>()

    private lateinit var useCase: GetCalendarUseCase

    @BeforeEach
    fun setup() {
        useCase = GetCalendarUseCase(repository)
    }

    @Test
    fun `invoke should return calendar`() = runTest {

        val calendar = Calendar().apply {
            add(
                CalendarItem(
                    date = "2025-05-14",
                    level = "HIGH",
                    risk = 80
                )
            )
        }

        val flow = flowOf(
            Resource.Success(calendar)
        )

        coEvery {
            repository.getCalendar(
                lat = 50f,
                lon = 30f,
                year = 2025,
                month = 5
            )
        } returns flow

        val result = useCase(
            lat = 50f,
            lon = 30f,
            year = 2025,
            month = 5
        ).first()

        assertTrue(result is Resource.Success)

        assertEquals(
            calendar,
            result.data
        )

        coVerify {
            repository.getCalendar(
                lat = 50f,
                lon = 30f,
                year = 2025,
                month = 5
            )
        }
    }
}