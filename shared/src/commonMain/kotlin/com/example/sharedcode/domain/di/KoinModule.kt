package com.example.sharedcode.domain.di


import com.example.sharedcode.common.changeBaseUrlInterceptor
import com.example.sharedcode.data.api.HomeApi
import com.example.sharedcode.data.api.HomeApiImpl
import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.data.repo.HomeRepositoryImp
import com.example.sharedcode.domain.usecase.HomeUseCase
import com.example.sharedcode.domain.usecase.HomeUseCaseImp
import com.example.sharedcode.getOriginalResponse
import com.example.sharedcode.getToken
import com.example.sharedcode.platformModule
import com.example.sharedcode.presentation.HomeViewModel
import com.example.sharedcode.security.decryptResponse
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
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

    single { getToken() }

    single { createJson() }

    single {
        createHttpClient(
            httpClientEngine = get(),
            token = get(),
            json = get()
        )
    }
}

fun dataModule() = module {
    single<HomeApi> { HomeApiImpl(get()) }
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
    token: String,
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

    install(Logging) {
        logger = Logger.SIMPLE
       level = LogLevel.ALL }

    install(ContentNegotiation) {
        json(json)
    }

    //TODO take byte array and return string from android and ios platform
    decryptResponse {
        callback = getOriginalResponse()
    }



}.apply {
    changeBaseUrlInterceptor()
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }





