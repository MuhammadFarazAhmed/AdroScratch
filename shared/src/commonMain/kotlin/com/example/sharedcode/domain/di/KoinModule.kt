package com.example.sharedcode.domain.di

import ApisEncryptionUtils
import com.example.sharedcode.common.changeBaseUrlInterceptor
import com.example.sharedcode.common.decryptResponse
import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.data.repo.HomeRepositoryImp
import com.example.sharedcode.domain.usecase.HomeUseCase
import com.example.sharedcode.domain.usecase.HomeUseCaseImp
import com.example.sharedcode.getToken
import com.example.sharedcode.platformModule
import com.example.sharedcode.presentation.HomeViewModel
import com.example.sharedcode.security.CLibController
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(commonModule())
}

// called by iOS etc
fun initKoin() = initKoin {}

fun commonModule() =
    platformModule() + useCaseModule() + dataModule() + networkModule() + viewModelModule()


fun networkModule() = module {
    single { CLibController() }

    single { ApisEncryptionUtils(get()) }

    single { getToken() }

    single { createJson() }

    single {
        createHttpClient(
            httpClientEngine = get(),
            cLibController = get(),
            token = get(),
            encryptionUtils = get(),
            json = get()
        )
    }
}

fun dataModule() = module {
    single<HomeRepository> { HomeRepositoryImp(get()) }
}

fun useCaseModule() = module {
    single<HomeUseCase> { HomeUseCaseImp(get()) }
}

fun viewModelModule() = module {
    single { HomeViewModel(get()) }
}

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    cLibController: CLibController,
    token: String,
    encryptionUtils: ApisEncryptionUtils,
    json: Json
) = HttpClient(httpClientEngine) {

    defaultRequest {
        url {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

            protocol = URLProtocol.HTTPS
            host = "apiutb2betentsrvpy.theentertainerme.com"
        }
    }

    install(Auth) {
        bearer {
            loadTokens {
                BearerTokens(token, token)
            }
        }
    }

    install(Logging) { level = LogLevel.ALL }

    install(ContentNegotiation) {
        json(json)
    }

    decryptResponse {
        apisEncryptionUtils = encryptionUtils
    }

}.apply {
    changeBaseUrlInterceptor(cLibController)
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }





