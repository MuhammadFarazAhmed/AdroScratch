package com.example.adro

import android.app.Application
import com.example.sharedcode.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class AdroApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        
        initKoin {
            androidContext(this@AdroApplication)
            androidLogger()
           // modules(listOf(module {  }))
        }
        
    }
}