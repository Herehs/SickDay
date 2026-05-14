package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
class CalendarDto : ArrayList<CalendarDtoItem>()

@Serializable
data class CalendarDtoItem(
    val date: String,
    val level: String,
    val risk: Int
)