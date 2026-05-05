package com.example.up.di

import com.example.up.data.remote.GetService
import com.example.up.data.remote.GetServiceImpl
import com.example.up.domain.use_case.GetWeatherInfoUseCase
import com.example.up.presentation.main_screen.MainViewmodel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewmodel)


    single { GetWeatherInfoUseCase() }
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