package com.example.up.domain.use_case

import com.example.up.domain.model.*
import com.example.up.domain.repository.SettingsRepository
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateSettingsUseCaseTest {

    private val repository = mockk<SettingsRepository>(relaxed = true)
    private lateinit var useCase: UpdateSettingsUseCase

    @Before
    fun setup() {
        useCase = UpdateSettingsUseCase(repository)
    }

    @Test
    fun `should call repository saveSettings with transform`() = runTest {

        val initial = UserSettings(
            email = "old@mail.com",
            password = "1234",
            gender = Gender.MALE,
            age = 20
        )

        val transformed = initial.copy(email = "new@mail.com")

        val transform: (UserSettings) -> UserSettings = {
            transformed
        }

        coEvery {
            repository.saveSettings(any())
        } just Runs

        useCase(transform)

        coVerify {
            repository.saveSettings(transform)
        }
    }
}