package com.example.up

import android.app.Application
import com.example.up.di.dataModule
import com.example.up.di.domainModule
import com.example.up.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@KoinApplication)
            modules(
                presentationModule,
                domainModule,
                dataModule
            )
        }
    }
}