package com.example.up.data.repository

import android.location.Location
import com.example.up.data.local.LocationProvider
import com.example.up.domain.repository.PositionRepository

class PositionRepositoryImpl(
    val positionProvider: LocationProvider
) : PositionRepository {
    override suspend fun getCurrentPosition(): Location {
        return positionProvider.getCurrentLocation()!!
    }
}