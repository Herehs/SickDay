package com.example.up.domain.model

import java.time.LocalDate

data class KpData(
    val Kp: Double,
    val a_running: Int,
    val station_count: Int,
    val time: LocalDate
)

