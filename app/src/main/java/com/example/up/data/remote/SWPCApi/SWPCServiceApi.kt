package com.example.up.data.remote.SWPCApi

import com.example.up.data.remote.dto.KpDataItem

interface SWPCServiceApi {
    suspend fun getKRData(): List<KpDataItem>
}