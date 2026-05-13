package com.example.up.data.local.mappers

import android.location.Location
import com.example.up.domain.model.Position

fun Location.toDomain(): Position{
    return Position(
        lat = this.latitude.toFloat(),
        lon = this.longitude.toFloat()
    )
}