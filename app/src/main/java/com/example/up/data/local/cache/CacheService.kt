package com.example.up.data.local.cache

import kotlinx.coroutines.flow.Flow

interface CacheService<T> {
    suspend fun load(): Flow<T>
    suspend fun save(data: T)
}

