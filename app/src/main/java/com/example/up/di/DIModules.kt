package com.example.up.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.up.data.local.SettingsSerializer
import com.example.up.data.remote.GetService
import com.example.up.data.remote.GetServiceImpl
import com.example.up.data.repository.SettingsRepositoryImpl
import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.SettingsRepository
import com.example.up.domain.use_case.GetSettingsUseCase
import com.example.up.domain.use_case.GetWeatherInfoUseCase
import com.example.up.domain.use_case.UpdateSettingsUseCase
import com.example.up.presentation.screens.main_screen.MainViewModel
import com.example.up.presentation.screens.settings_screen.SettingsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.io.File

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingsViewModel)

}

val dataModule = module {

    single<DataStore<UserSettings>> {
        DataStoreFactory.create(
            serializer = SettingsSerializer,
            produceFile = { File( get<Context>().filesDir,"settings.json") }
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
    single { GetSettingsUseCase(get()) }

    single { UpdateSettingsUseCase(get()) }

    single { GetWeatherInfoUseCase() }
}