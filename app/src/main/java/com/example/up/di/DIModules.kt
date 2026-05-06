package com.example.up.di

import android.content.Context
import android.content.SharedPreferences
import com.example.up.data.remote.GetService
import com.example.up.data.remote.GetServiceImpl
import com.example.up.data.repository.SettingsRepositoryImpl
import com.example.up.domain.repository.SettingsRepository
import com.example.up.domain.use_case.GetWeatherInfoUseCase
import com.example.up.presentation.main_screen.MainViewmodel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val presentationModule = module {
    viewModelOf(::MainViewmodel)


    single { GetWeatherInfoUseCase() }

}

val dataModule = module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            "prefs",
            Context.MODE_PRIVATE
        )
    }

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

    single<SettingsRepository>{
        SettingsRepositoryImpl(get())
    }
}

val domainModule = module {

}