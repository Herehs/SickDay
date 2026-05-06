package com.example.up.data.repository

import android.content.SharedPreferences
import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val prefs: SharedPreferences
) : SettingsRepository {
    override fun getSettings(): UserSettings {
        TODO("Not yet implemented")
    }

    override fun saveSettings(settings: UserSettings) {
        prefs.edit().apply {
            putString("email", settings.email)
            putString("password", settings.password)
            putInt("age", settings.age)
            TODO("Доделать настройки")
            apply()
        }
    }
}