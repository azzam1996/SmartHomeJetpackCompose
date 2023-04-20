package com.azzam.smarthomejetpackcompose

import android.app.Application
import com.azzam.smarthomejetpackcompose.di.viewModelModule
import org.koin.core.context.startKoin

class SmartHomeApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(viewModelModule)
        }
    }
}