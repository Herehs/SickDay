package com.example.up.data.local.location

import android.location.Location

interface LocationProvider {
    suspend fun getCurrentLocation(): Location?
}