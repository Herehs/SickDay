package com.example.up.data.remote

import com.example.up.data.remote.dto.GetResponse


interface GetService {
    suspend fun get(): List<GetResponse>
}