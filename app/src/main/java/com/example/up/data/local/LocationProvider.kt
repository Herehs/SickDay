package com.example.up.data.local

import android.location.Location

interface LocationProvider {
    suspend fun getCurrentLocation(): Location?
}