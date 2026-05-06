package com.example.up.domain.repository

import com.example.up.domain.model.UserSettings

interface SettingsRepository {
    fun getSettings(): UserSettings
    fun saveSettings(settings: UserSettings)
}