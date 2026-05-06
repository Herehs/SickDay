package com.example.up.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class UserSettings(
    val email: String = "",
    val password: String = "",
    val gender: Gender = Gender.MALE,
    val age: Int = 0,
    val notificationSettings: NotificationSettings = NotificationSettings()
)

@Serializable
data class NotificationSettings(
    val enabled: Boolean = false,
    val tempSettings: TempSettings = TempSettings(),
    val humiditySettings: HumiditySettings = HumiditySettings()
)

@Serializable
data class TempSettings(
    val enabled: Boolean = false,
    val tempLowThreshold: Int = 0,
    val tempHighThreshold: Int = 0
)

@Serializable
data class HumiditySettings(
    val enabled: Boolean = false,
    val humidityThreshold: Int = 0
)

@Serializable
sealed class Gender(val text: String){
    object MALE : Gender("Мужской")
    object FEMALE : Gender("Женский")
}

