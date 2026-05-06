package com.example.up.domain.use_case

import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.SettingsRepository

class UpdateSettingsUseCase(
    private val repository: SettingsRepository
) {
    suspend operator fun invoke(
        transform: (UserSettings) -> UserSettings
    ) {
        repository.saveSettings(transform)
    }
}