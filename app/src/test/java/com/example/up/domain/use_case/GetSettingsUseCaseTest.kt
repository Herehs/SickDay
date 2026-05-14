package com.example.up.domain.use_case

import com.example.up.domain.model.*
import com.example.up.domain.repository.SettingsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetSettingsUseCaseTest {

    private val repository = mockk<SettingsRepository>()
    private lateinit var useCase: GetSettingsUseCase

    @Before
    fun setup() {
        useCase = GetSettingsUseCase(repository)
    }

    @Test
    fun `should return settings flow`() = runTest {

        val settings = UserSettings(
            email = "test@mail.com",
            password = "1234",
            gender = Gender.MALE,
            age = 25
        )

        val flow = flowOf(settings)

        coEvery {
            repository.getSettings()
        } returns flow

        val result = useCase().first()

        assertEquals(settings, result)

        coVerify {
            repository.getSettings()
        }
    }
}