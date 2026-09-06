package com.example.up.data.local.mappers

import com.example.up.data.local.cache.dto.PositionCache
import com.example.up.domain.model.Position

fun PositionCache.toDomain() = Position(
    lat = position.lat,
    lon = position.lon
)

fun Position.toData(savedAtMs: Long) = PositionCache(
    position = this,
    savedAtMs = savedAtMs
)