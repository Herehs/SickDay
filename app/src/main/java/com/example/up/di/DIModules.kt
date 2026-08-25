package com.example.up.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.room3.Room
import com.example.up.data.local.location.LocationProvider
import com.example.up.data.local.location.LocationProviderImpl
import com.example.up.data.local.SettingsSerializer
import com.example.up.data.local.database.AppDatabase
import com.example.up.data.remote.WeatherServiceApi
import com.example.up.data.remote.WeatherServiceApiImpl
import com.example.up.data.repository.NoteRepositoryImpl
import com.example.up.data.repository.PositionRepositoryImpl
import com.example.up.data.repository.SettingsRepositoryImpl
import com.example.up.data.repository.WeatherRepositoryImpl
import com.example.up.domain.model.UserSettings
import com.example.up.domain.repository.NoteRepository
import com.example.up.domain.repository.PositionRepository
import com.example.up.domain.repository.SettingsRepository
import com.example.up.domain.repository.WeatherRepository
import com.example.up.domain.use_case.GetAvgWeatherUseCase
import com.example.up.domain.use_case.GetCalendarUseCase
import com.example.up.domain.use_case.GetCurrentPositionUseCase
import com.example.up.domain.use_case.GetCurrentWeatherUseCase
import com.example.up.domain.use_case.GetGraphDataUseCase
import com.example.up.domain.use_case.GetSettingsUseCase
import com.example.up.domain.use_case.UpdateSettingsUseCase
import com.example.up.presentation.screens.calendar_screen.CalendarViewModel
import com.example.up.presentation.screens.main_screen.MainViewModel
import com.example.up.presentation.screens.settings_screen.SettingsViewModel
import com.google.android.gms.location.LocationServices
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import java.io.File

val presentationModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::CalendarViewModel)
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
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "sickday_db"
        ).fallbackToDestructiveMigration(true).build()
    }
    single { get<AppDatabase>().noteDao() }

    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json{
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 10_000
                socketTimeoutMillis = 15_000
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
        WeatherRepositoryImpl(get())
    }

    single<SettingsRepository>{
        SettingsRepositoryImpl(get())
    }

    single<PositionRepository> {
        PositionRepositoryImpl(get())
    }
    single<NoteRepository> {
        NoteRepositoryImpl(get())
    }
}

val domainModule = module {
    //use cases
    single { GetSettingsUseCase(get()) }

    single { UpdateSettingsUseCase(get()) }

    single { GetCurrentWeatherUseCase(get()) }

    single { GetAvgWeatherUseCase(get()) }

    single { GetGraphDataUseCase(get()) }

    single { GetCurrentPositionUseCase(get()) }

    single { GetCalendarUseCase(get()) }
}