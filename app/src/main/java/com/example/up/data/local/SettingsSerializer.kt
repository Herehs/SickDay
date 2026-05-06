package com.example.up.data.local

import androidx.datastore.core.Serializer
import com.example.up.domain.model.UserSettings
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

@Suppress("BlockingMethodInNonBlockingContext")
object SettingsSerializer : Serializer<UserSettings> {
    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        return try {
            Json.decodeFromString(
                deserializer = UserSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (e: SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(
        t: UserSettings,
        output: OutputStream
    ) {
        output.write(
            Json.encodeToString(
                serializer = UserSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}