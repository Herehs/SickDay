package com.example.up.data.remote.mappers

import com.example.up.data.remote.dto.CalendarDto
import com.example.up.data.remote.dto.CalendarDtoItem
import com.example.up.domain.model.Calendar
import com.example.up.domain.model.CalendarItem

fun CalendarDto.toDomain(): Calendar{
    val itemList = this.toDomain()

    return itemList
}

fun CalendarDtoItem.toDomain(): CalendarItem{
    return CalendarItem(
        date = date,
        level = level,
        risk = risk
    )
}