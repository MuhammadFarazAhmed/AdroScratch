package com.example.sharedcode

import org.koin.core.module.Module


interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun platformModule(): Module

expect fun getToken(): String

expect fun getOriginalResponse(): suspend (String) -> String?


@OptIn(ExperimentalMultiplatform::class)
@OptionalExpectation
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.BINARY)
expect annotation class CommonParcelize()

expect interface CommonParcelable