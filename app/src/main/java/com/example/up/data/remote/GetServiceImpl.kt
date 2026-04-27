package com.example.up.data.remote

import com.example.up.data.remote.dto.GetResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*


class GetServiceImpl(
    private val client: HttpClient
) : GetService {
    override suspend fun get(): List<GetResponse> {
        return client.get {
            url(HttpRoutes.GET)
        }.body()
    }
}