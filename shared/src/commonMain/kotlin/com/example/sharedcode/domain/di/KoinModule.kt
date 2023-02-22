package com.example.sharedcode.domain.di


import com.example.sharedcode.*
import com.example.sharedcode.common.changeBaseUrlInterceptor
import com.example.sharedcode.domain.usecase.*
import com.example.sharedcode.home.data.api.HomeApi
import com.example.sharedcode.home.data.api.HomeApiImpl
import com.example.sharedcode.home.data.repo.HomeRepository
import com.example.sharedcode.home.data.repo.HomeRepositoryImp
import com.example.sharedcode.home.presentation.HomeViewModel
import com.example.sharedcode.offers.data.api.MerchantApi
import com.example.sharedcode.offers.data.api.MerchantApiImpl
import com.example.sharedcode.offers.data.repo.MerchantRepository
import com.example.sharedcode.offers.data.repo.MerchantRepositoryImp
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import com.example.sharedcode.profile.data.repo.ProfileRepository
import com.example.sharedcode.profile.data.repo.ProfileRepositoryImp
import com.example.sharedcode.profile.presentation.ProfileViewModel
import com.example.sharedcode.security.decryptResponse
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

    single { getToken() }

    single { createJson() }

    single { AesCipher() }

    single {
        createHttpClient(
            httpClientEngine = get(),
            token = get(),
            json = get(),
            aesCipher = get()
        )
    }
}

fun dataModule() = module {
    single<HomeApi> { HomeApiImpl(get()) }
    single<MerchantApi> { MerchantApiImpl(get()) }

    single<HomeRepository> { HomeRepositoryImp(get()) }
    single<MerchantRepository> { MerchantRepositoryImp(get()) }

    single<ProfileRepository> { ProfileRepositoryImp(get()) }
}

fun useCaseModule() = module {
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    single<MerchantUseCase> { MerchantUseCaseImp(get()) }
    single<ProfileUseCase> { ProfileUseCaseImp(get()) }
}

fun viewModelModule() = module {
    single { HomeViewModel(get()) }
    single { OffersSharedViewModel(get()) }
    single { ProfileViewModel(get()) }
}

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    token: String,
    json: Json,
    aesCipher: AesCipher
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
        level = LogLevel.ALL
    }

    install(ContentNegotiation) {
        json(json)
    }

    // take byte array and return string from android and ios platform
    decryptResponse {
        cipher = aesCipher
    }


}.apply {
    changeBaseUrlInterceptor()
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }





