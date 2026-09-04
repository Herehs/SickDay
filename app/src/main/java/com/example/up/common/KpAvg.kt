package com.example.up.common

import com.example.up.domain.model.KpData
import java.time.LocalDate

fun List<KpData>.averageForDate(date: LocalDate): Double {
    val dayData = filter { it.time == date }
    return if (dayData.isEmpty()) 0.0 else dayData.map { it.Kp }.average()
}