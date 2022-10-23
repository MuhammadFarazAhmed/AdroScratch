package com.example.adro

import android.app.Application
import com.example.sharedcode.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class AdroApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        initKoin {
            androidContext(this@AdroApplication)
            modules(listOf())
        }
        
    }
}