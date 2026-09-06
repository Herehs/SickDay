package com.example.up.data.local.cache

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import com.example.up.data.local.cache.dto.PositionCache
import com.example.up.domain.model.Position
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PositionCacheServiceImpl(
    private val datastore: DataStore<Preferences>
): CacheService<PositionCache> {
    private val lat = doublePreferencesKey("lat")
    private val lon = doublePreferencesKey("lon")
    private val savedAtMs = longPreferencesKey("savedAtMs")

    override suspend fun load(): Flow<PositionCache> = datastore.data.map { prefs ->
        val position = Position(
            lat = prefs[lat] ?: .0,
            lon = prefs[lon] ?: .0
        )
        PositionCache(
            position = position,
            savedAtMs = prefs[savedAtMs] ?: 0L
        )
    }

    override suspend fun save(data: PositionCache) {
        datastore.updateData {
            it.toMutablePreferences().also { prefs ->
                prefs[lat] = data.position.lat
                prefs[lon] = data.position.lon
                prefs[savedAtMs] = System.currentTimeMillis()
            }
        }
    }
}