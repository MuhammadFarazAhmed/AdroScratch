package com.example.adro.di

import android.provider.ContactsContract.Data
import android.util.Base64
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.adro.interceptors.changeBaseUrlInterceptor
import com.example.adro.interceptors.decryptResponse
import com.example.adro.prefs.ConfigPreferencesSerializer
import com.example.adro.prefs.PreferencesHelper
import com.example.adro.prefs.UserPreferencesSerializer
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.vm.CommonViewModel
import com.example.auth.vm.AuthViewModel
import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.models.ErrorResponse
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import com.example.domain.repos.CommonRepository
import com.example.domain.repos.FavoritesRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.MerchantRepository
import com.example.domain.repos.ProfileRepository
import com.example.domain.usecase.AuthUseCase
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
import com.example.repositories.usecases.AuthRepositoryImp
import com.example.repositories.usecases.AuthUseCaseImp
import com.example.repositories.usecases.CommonUseCaseImp
import com.example.repositories.usecases.FavUseCaseImp
import com.example.repositories.usecases.HomeUseCaseImp
import com.example.repositories.usecases.MerchantUseCaseImp
import com.example.repositories.usecases.ProfileUseCaseImp
import com.theentertainerme.adro.security.CLibController
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.statement.HttpResponseContainer
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun appModule() = listOf(AppModule, NetworkModule)

fun networkModule() = listOf(NetworkModule)

fun featureModules() = listOf(commonModule, authModule, homeModule, merchantModule, profileModule)

public enum class DataStores {
    CONFIG, USER, APP
}

val AppModule = module {

    single(named(DataStores.APP)) {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }),
            produceFile = { androidContext().preferencesDataStoreFile("intamiDataStore") },
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        )
    }

    single(named(DataStores.CONFIG)) {
        DataStoreFactory.create(
            serializer = ConfigPreferencesSerializer,
            corruptionHandler = null,
            produceFile = { androidContext().dataStoreFile("configDataStore.json") },
            scope = CoroutineScope(
                Dispatchers.IO + SupervisorJob()
            )
        )
    }

    single(named(DataStores.USER)) {
        DataStoreFactory.create(
            serializer = UserPreferencesSerializer,
            corruptionHandler = null,
            produceFile = { androidContext().dataStoreFile("userDataStore.json") },
            scope = CoroutineScope(
                Dispatchers.IO + SupervisorJob()
            )
        )
    }

    single {
        PreferencesHelper(get(named(DataStores.APP)))
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

    single { CLibController }

    single { ApisEncryptionUtils(get()) }

}


@OptIn(InternalAPI::class)
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
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            changeBaseUrlInterceptor()

            decryptResponse {
                apisEncryptionUtils = get()
            }

        }
    }
}

val commonModule = module {
    single<CommonRepository> { CommonRepositoryImp(get(), get(named(DataStores.CONFIG))) }
    single<CommonUseCase> { CommonUseCaseImp(get()) }
    viewModel { CommonViewModel(get(), get()) }
}

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImp(get()) }
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    viewModel { HomeViewModel(get(), get(), get(named(DataStores.CONFIG))) }
}

val authModule = module {
    single<AuthRepository> { AuthRepositoryImp(get(), get(named(DataStores.USER))) }
    single<AuthUseCase> { AuthUseCaseImp(get()) }
    viewModel { AuthViewModel(get(), get()) }
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
