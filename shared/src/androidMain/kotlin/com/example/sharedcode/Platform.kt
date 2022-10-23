package com.example.sharedcode

import android.os.Parcelable
import com.example.sharedcode.presentation.HomeViewModel
import io.ktor.client.engine.android.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {
    single {
        Android.create()
    }
}

actual typealias CommonParcelable = Parcelable