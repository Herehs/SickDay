package com.example.up.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Routes(){
    @Serializable
    object MainScreen : Routes()

    @Serializable
    object CalendarScreen : Routes()

    @Serializable
    object Notes : Routes()
    @Serializable
    data class NoteDetails(
        val id: Long?
    ) : Routes()

    @Serializable
    object Onboarding : Routes()
}