package com.example.up.common

import com.example.up.domain.model.GraphData
import com.example.up.domain.model.KpData
import java.time.LocalDate

fun List<KpData>.toGraphData(date: LocalDate): GraphData {
    val values = filter { it.time == date }.map { it.Kp.toFloat() }
    return GraphData(list = values.ifEmpty { listOf(0f) })
}