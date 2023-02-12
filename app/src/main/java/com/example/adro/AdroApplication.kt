package com.example.adro

import android.app.Application
import com.example.offers.vm.OffersViewModel
import com.example.sharedcode.domain.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module

class AdroApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@AdroApplication)
            androidLogger()
            modules(
                listOf(module { single { OffersViewModel(get()) } })
            )
        }

    }
}