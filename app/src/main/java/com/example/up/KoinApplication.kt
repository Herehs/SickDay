package com.example.up

import android.app.Application
import com.example.up.di.mainModule
import com.example.up.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(
                mainModule,
                networkModule
            )
        }
    }
}