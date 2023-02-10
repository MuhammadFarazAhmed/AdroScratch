package com.example.sharedcode

import com.example.sharedcode.presentation.HomeViewModel
import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual fun getToken(): String {
    //TODO get the token from swift
    return ""
}


actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    single {
        HomeViewModel(get())
    }

    single { CLibController }

    //single or factory can be used to get a view-model object for swiftui
}

actual interface CommonParcelable

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
}

actual fun getOriginalResponse(): suspend (response: String) -> String? = "" //TODO get the decrypted response from CryptoKit Swift

