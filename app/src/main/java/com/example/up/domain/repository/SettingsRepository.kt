package com.example.up.domain.repository

import com.example.up.domain.model.UserSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    fun getSettings(): Flow<UserSettings>
    suspend fun saveSettings(transform: (UserSettings) -> UserSettings)
}