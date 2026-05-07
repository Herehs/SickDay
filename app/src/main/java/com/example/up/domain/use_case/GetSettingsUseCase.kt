package com.example.up.domain.use_case

import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetSettingsUseCase(
    private val repository: SettingsRepository
) {
    operator fun invoke(): Flow<UserSettings> = repository.getSettings()
}