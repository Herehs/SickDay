package com.example.up.data.remote.SWPCApi

import android.util.Log
import com.example.up.data.remote.dto.KpDataItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SWPCServiceApiImpl(
    private val client: HttpClient
) : SWPCServiceApi {
    private var cache: List<KpDataItem>? = null
    private var cacheTimestamp: Long = 0L
    private val mutex = Mutex()
    private val cacheTtlMillis = 5 * 60 * 1000L

    override suspend fun getKRData(): List<KpDataItem> = mutex.withLock{
        val isExpired = System.currentTimeMillis() - cacheTimestamp > cacheTtlMillis
        val current = cache

        if (current == null || isExpired) {
            val fresh = try {
                client.get("https://services.swpc.noaa.gov/products/noaa-planetary-k-index.json")
                    .body<List<KpDataItem>>()
            } catch (e: Exception) {
                Log.e("Kp", "Fetch/parse failed", e)
                throw e
            }
            cache = fresh
            cacheTimestamp = System.currentTimeMillis()

            fresh
        } else {
            current
        }
    }
}