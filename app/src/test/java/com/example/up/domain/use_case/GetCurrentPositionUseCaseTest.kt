package com.example.up.domain.use_case

import com.example.up.common.Resource
import com.example.up.domain.model.Position
import com.example.up.domain.repository.PositionRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetCurrentPositionUseCaseTest {

    private val repository = mockk<PositionRepository>()

    private lateinit var useCase: GetCurrentPositionUseCase

    @Before
    fun setup() {
        useCase = GetCurrentPositionUseCase(repository)
    }

    @Test
    fun `invoke should return current position`() = runTest {

        val position = Position(
            lat = 50f,
            lon = 30f
        )

        val flow = flowOf(
            Resource.Success(position)
        )

        coEvery {
            repository.getCurrentPosition()
        } returns flow

        val result = useCase().first()

        assertTrue(result is Resource.Success)

        assertEquals(
            position,
            result.data
        )

        coVerify {
            repository.getCurrentPosition()
        }
    }
}