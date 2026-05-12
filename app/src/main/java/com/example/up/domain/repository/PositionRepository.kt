package com.example.up.domain.repository

import android.location.Location

interface PositionRepository {
    suspend fun getCurrentPosition(): Location
}