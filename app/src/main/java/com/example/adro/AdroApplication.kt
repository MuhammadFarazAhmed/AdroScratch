package com.example.adro

import android.app.Application
import android.app.Service
import com.example.adro.di.AppModule
import com.example.adro.di.appModule
import com.example.adro.di.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AdroApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AdroApplication)
            modules(appModule() + featureModules())
        }
        
    }
}