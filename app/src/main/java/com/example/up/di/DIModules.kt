package com.example.up.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import com.example.up.data.local.LocationProvider
import com.example.up.data.local.LocationProviderImpl
import com.example.up.data.local.SettingsSerializer
import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.WeatherServiceApiImpl
import com.example.up.data.repository.PositionRepositoryImpl
import com.example.up.data.repository.SettingsRepositoryImpl
import com.example.up.data.repository.test.WeatherRepositoryTest
import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.PositionRepository
import com.example.up.domain.repository.SettingsRepository
import com.example.up.domain.repository.WeatherRepository
import com.example.up.domain.use_case.GetCurrentPositionUseCase
import com.example.up.domain.use_case.GetCurrentWeatherUseCase
import com.example.up.domain.use_case.GetSettingsUseCase
import com.example.up.domain.use_case.UpdateSettingsUseCase
import com.example.up.presentation.screens.main_screen.MainViewModel
import com.example.up.presentation.screens.settings_screen.SettingsViewModel
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.io.File
import kotlin.math.sin

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::SettingsViewModel)

}

val dataModule = module {
    //data providers
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

    single<WeatherServiceApi> {
        WeatherServiceApiImpl(get())
    }

    single {
        LocationServices.getFusedLocationProviderClient(get<Context>())
    }

    single<LocationProvider>{
        LocationProviderImpl(get<Context>(), get())
    }

    //repositories
    single<WeatherRepository>{
        WeatherRepositoryTest(get())
    }

    single<SettingsRepository>{
        SettingsRepositoryImpl(get())
    }

    single<PositionRepository> {
        PositionRepositoryImpl(get())
    }
}

val domainModule = module {
    //use cases
    single { GetSettingsUseCase(get()) }

    single { UpdateSettingsUseCase(get()) }

    single { GetCurrentWeatherUseCase(get()) }

    single { GetCurrentPositionUseCase(get()) }
}