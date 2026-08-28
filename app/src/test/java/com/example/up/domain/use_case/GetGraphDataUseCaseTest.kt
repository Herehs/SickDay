package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.GraphData
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

class GetGraphDataUseCaseTest {

    private val repository = mockk<WeatherRepository>()
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
            Resource.Success(graphData)
        )

        coEvery {
            repository.getGraphData(
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

        assertTrue(result is Resource.Success)

        assertEquals(
            graphData,
            result.data
        )

        coVerify {
            repository.getGraphData(
                lat = 50f,
                lon = 30f,
                date = "2025-05-14"
            )
        }
    }
}