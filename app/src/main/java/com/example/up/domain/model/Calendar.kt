package com.example.up.domain.model


class Calendar : ArrayList<CalendarItem>()

data class CalendarItem(
    val date: String,
    val level: String,
    val risk: Int
)