package com.example.up.di

import com.example.up.data.remote.GetService
import com.example.up.data.remote.GetServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val mainModule = module {

}

val networkModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json()
            }
        }
    }

    single<GetService> {
        GetServiceImpl(get())
    }
}