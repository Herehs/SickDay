package com.example.up.data.local.cache.dto

import com.example.up.domain.model.Position

data class PositionCache(
    val position: Position = Position(.0, .0),
    val savedAtMs: Long = 0L
)
