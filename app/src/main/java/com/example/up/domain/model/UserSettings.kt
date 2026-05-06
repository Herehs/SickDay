package com.example.up.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer


@Serializable
data class UserSettings(
    val email: String,
    val password: String,
    val gender: Gender,
    val age: Int,
    val notificationSettings: NotificationSettings
)

@Serializable
data class NotificationSettings(
    val enabled: Boolean,
    val tempSettings: TempSettings,
    val humiditySettings: HumiditySettings
)

@Serializable
data class TempSettings(
    val enabled: Boolean,
    val tempLowThreshold: Int,
    val tempHighThreshold: Int,
)

@Serializable
data class HumiditySettings(
    val enabled: Boolean,
    val humidityThreshold: Int
)

@Serializable
sealed class Gender(val text: String){
    object MALE : Gender("Мужской")
    object FEMALE : Gender("Женский")
}

