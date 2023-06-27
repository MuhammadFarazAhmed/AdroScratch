package com.example.adro

import android.app.Application
import com.example.adro.di.appModule
import com.example.adro.di.featureModules
import com.example.adro.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ThriveApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@ThriveApplication)
            modules(appModule() + networkModule() + featureModules())
        }
    }
}