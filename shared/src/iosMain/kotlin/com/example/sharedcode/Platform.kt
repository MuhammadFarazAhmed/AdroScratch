package com.example.sharedcode

import platform.UIKit.UIDevice

import io.ktor.client.engine.darwin.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    //single or factory can be used to get a view-model object for swiftui
}
