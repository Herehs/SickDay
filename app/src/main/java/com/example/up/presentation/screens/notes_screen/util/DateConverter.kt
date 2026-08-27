package com.example.up.presentation.screens.notes_screen.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId


fun Long.toLocalDate(): LocalDate {
    return Instant
        .ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

