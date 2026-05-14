package com.example.up.data.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class GraphDtoItem(
    val hour_range: String,
    val risk: Int
)