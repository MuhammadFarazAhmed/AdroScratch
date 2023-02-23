package com.example.sharedcode

import com.example.sharedcode.home.presentation.HomeViewModel
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import io.ktor.client.engine.darwin.*
import io.ktor.utils.io.core.*
import kotlinx.cinterop.usePinned
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual fun getToken(): String = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJjb21wYW55IjoiQURPIiwic2Vzc2lvbl90b2tlbiI6IiIsImFwaV90b2tlbiI6ImsyMjlybi1qIzVXOS1KOEQjNi1BNk0wKG8tITcjOSY0JHgifQ.a7bh0HsSHslpv_hcloCTvADStDVbNvK0zUZ9Gn5sSMw"


actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    single {
        HomeViewModel(get())
    }

    //single or factory can be used to get a view-model object for swiftui
}

actual interface CommonParcelable

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
    fun getOfferSharedViewModel() = get<OffersSharedViewModel>()
}

actual fun getOriginalResponse(): suspend (String) -> String? = { "" } //TODO get the decrypted response from CryptoKit Swift

actual class CryptoService actual constructor() {
    actual fun encrypt(plainText: String, key: ByteArray, iv: ByteArray): ByteArray {
        return byteArrayOf()
    }

    actual fun decrypt(cipherText: ByteArray, key: ByteArray, iv: ByteArray): String {
       return ""
    }
}

