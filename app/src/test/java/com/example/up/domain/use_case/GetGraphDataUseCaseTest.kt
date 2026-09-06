package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.GraphData
import com.example.up.domain.model.KpData
import com.example.up.domain.repository.KpRepository
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
import java.time.LocalDate

class GetGraphDataUseCaseTest {

    private val repository = mockk<KpRepository>()
    private lateinit var useCase: GetGraphDataUseCase

    @BeforeEach
    fun setup() {
        useCase = GetGraphDataUseCase(repository)
    }

    @Test
    fun `should return graph data`() = runTest {

        val graphData = GraphData(
            list = listOf(1f, 2f, 3f)
        )

        val flow = flowOf(
            Resource.Success(listOf(KpData(
                Kp = .1,
                a_running = 1,
                station_count = 8,
                time = LocalDate.of(2025, 5,15)
            )))
        )


        coEvery {
            repository.getKpData()
        } returns flow

        val result = useCase(
            lat = 50f,
            lon = 30f,
            date = "2025-05-14"
        ).first()

        assertTrue(result is Resource.Success)

        assertEquals(
            graphData,
            result.data
        )

        coVerify {
            repository.getKpData()
        }
    }
}