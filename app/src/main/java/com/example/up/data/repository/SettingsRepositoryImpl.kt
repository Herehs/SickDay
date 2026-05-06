package com.example.up.data.repository

import androidx.datastore.core.DataStore
import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class SettingsRepositoryImpl(
    private val dataStore: DataStore<UserSettings>
) : SettingsRepository {
    override fun getSettings(): Flow<UserSettings> = dataStore.data

    override suspend fun saveSettings(transform: (UserSettings) -> UserSettings) {
        dataStore.updateData {
            transform(it)
        }
    }
}