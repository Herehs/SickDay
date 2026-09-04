package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class KpDataItem(
    val Kp: Double,
    val a_running: Int,
    val station_count: Int,
    val time_tag: String
)