package com.example.adro.di

import android.util.Base64
import com.example.adro.common.PreferencesHelper
import com.example.adro.common.changeBaseUrlInterceptor
import com.example.adro.common.decryptResponse
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.vm.CommonViewModel
import com.example.domain.repos.CommonRepository
import com.example.domain.repos.FavoritesRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.MerchantRepository
import com.example.domain.repos.ProfileRepository
import com.example.domain.usecase.CommonUseCase
import com.example.domain.usecase.FavUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.MerchantUseCase
import com.example.domain.usecase.ProfileUseCase
import com.example.home.vm.HomeViewModel
import com.example.offers.vm.FavoriteViewModel
import com.example.offers.vm.OffersViewModel
import com.example.profile.vm.ProfileViewModel
import com.example.repositories.repos.CommonRepositoryImp
import com.example.repositories.repos.FavRepositoryImp
import com.example.repositories.repos.HomeRepositoryImp
import com.example.repositories.repos.MerchantRepositoryImp
import com.example.repositories.repos.ProfileRepositoryImp
import com.example.repositories.usecases.CommonUseCaseImp
import com.example.repositories.usecases.FavUseCaseImp
import com.example.repositories.usecases.HomeUseCaseImp
import com.example.repositories.usecases.MerchantUseCaseImp
import com.example.repositories.usecases.ProfileUseCaseImp
import com.theentertainerme.adro.security.CLibController
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


fun appModule() = listOf(AppModule, NetworkModule)

fun networkModule() = listOf(NetworkModule)

fun featureModules() = listOf(commonModule, homeModule, merchantModule, profileModule)

val AppModule = module {

    single {
        PreferencesHelper(androidContext())
    }

    single<String> {
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", get<PreferencesHelper>().getCompany())
            .claim("session_token", get<PreferencesHelper>().getSessionToken())
            .claim("api_token", get<PreferencesHelper>().getApiToken())
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(
                    get<PreferencesHelper>().getSRKey().toByteArray(),
                    Base64.DEFAULT
                )
            ).compact()

    }

    single { CLibController() }

    single { ApisEncryptionUtils(get()) }

}

val NetworkModule = module {
    single {
        HttpClient(Android) {

            defaultRequest {
                url {
                    contentType(ContentType.Application.Json)
                    accept(ContentType.Application.Json)

                    protocol = URLProtocol.HTTPS
                    host = get<CLibController>().getENTBaseUrlOnline()
                }
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(get(), get())
                    }
                }
            }

            install(Logging) { level = LogLevel.ALL }

            install(ContentNegotiation) {
                gson {
                    serializeNulls()
                }
            }

            decryptResponse {
                apisEncryptionUtils = get()
            }

        }.apply {
            changeBaseUrlInterceptor(get())
        }
    }
}

val commonModule = module {
    single<CommonRepository> { CommonRepositoryImp(get()) }
    single<CommonUseCase> { CommonUseCaseImp(get()) }
    viewModel { CommonViewModel(get(), get()) }
}

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImp(get()) }
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    viewModel { HomeViewModel(get(), get()) }
}

val merchantModule = module {

    single<MerchantRepository> { MerchantRepositoryImp(get()) }
    single<FavoritesRepository> { FavRepositoryImp(get()) }

    single<MerchantUseCase> { MerchantUseCaseImp(get()) }
    single<FavUseCase> { FavUseCaseImp(get()) }

    viewModel { OffersViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get(), get()) }
}

val profileModule = module {
    single<ProfileRepository> { ProfileRepositoryImp(get()) }
    single<ProfileUseCase> { ProfileUseCaseImp(get()) }
    viewModel { ProfileViewModel(get(), get()) }
}
