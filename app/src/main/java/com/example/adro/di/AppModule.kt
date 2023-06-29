package com.example.adro.di

import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.adro.BearerTokenProvider
import com.example.adro.interceptors.changeBaseUrlInterceptor
import com.example.adro.interceptors.decryptResponse
import com.example.adro.prefs.ConfigPreferencesSerializer
import com.example.adro.prefs.PreferencesHelper
import com.example.adro.prefs.UserPreferencesSerializer
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.vm.CommonViewModel
import com.example.auth.vm.AuthViewModel
import com.example.adro.models.ErrorResponse
import com.example.domain.repos.AuthRepository
import com.example.domain.repos.CommonRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.CommonUseCase
import com.example.domain.usecase.FavUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.MerchantUseCase
import com.example.home.vm.HomeViewModel
import com.example.offers.vm.FavoriteViewModel
import com.example.offers.vm.OffersViewModel
import com.example.offers.vm.SearchViewModel
import com.example.profile.vm.ProfileViewModel
import com.example.repositories.repos.CommonRepositoryImp
import com.example.repositories.repos.HomeRepositoryImp
import com.example.repositories.repos.MerchantRepositoryImp
import com.example.repositories.usecases.AuthRepositoryImp
import com.example.repositories.usecases.AuthUseCaseImp
import com.example.repositories.usecases.CommonUseCaseImp
import com.example.repositories.usecases.FavUseCaseImp
import com.example.repositories.usecases.HomeUseCaseImp
import com.example.repositories.usecases.MerchantUseCaseImp
import com.theentertainerme.adro.security.CLibController
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import io.ktor.utils.io.readUTF8Line
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

fun featureModules() = listOf(commonModule, authModule, homeModule, merchantModule, searchModule)

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

    single {
        BearerTokenProvider(get(named(DataStores.USER)))
    }

    single { ApisEncryptionUtils(CLibController) }

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
                    host = CLibController.getENTBaseUrlOnline()
                }
            }

            Auth {
                this.providers.add(get<BearerTokenProvider>())
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

            expectSuccess = true

            HttpResponseValidator {
                handleResponseExceptionWithRequest { cause, _ ->
                    when (cause) {
                        is ClientRequestException -> {
                            if (cause.response.status == HttpStatusCode.UnprocessableEntity) {
                                cause.response.content.readUTF8Line()?.let {
                                    val error = Json.decodeFromString(
                                        deserializer = ErrorResponse.serializer(),
                                        it.byteInputStream().readBytes().decodeToString()
                                    )
                                    throw Exception(
                                        error.message.toString(),
                                        cause
                                    )
                                }
                            }
                        }

                        else -> {
                            throw Exception(cause)
                        }
                    }
                }
            }
        }
    }
}

val commonModule = module {
    single<CommonRepository> { CommonRepositoryImp(get(), get(named(DataStores.CONFIG))) }
    single<CommonUseCase> { CommonUseCaseImp(get()) }
    viewModel { CommonViewModel(get(), get(), get()) }
}

val homeModule = module {
    single<HomeRepository> { HomeRepositoryImp(get()) }
    single<HomeUseCase> { HomeUseCaseImp(get()) }
    viewModel { HomeViewModel(get(), get(), get()) }
}

val authModule = module {

    single<AuthRepository> { AuthRepositoryImp(get(), get(named(DataStores.USER))) }
    single<AuthUseCase> { AuthUseCaseImp(get()) }

    viewModel { AuthViewModel(get(), get()) }
    viewModel { ProfileViewModel(get(), get()) }

}

val searchModule = module {
    viewModel { SearchViewModel(get(), get()) }
}


val merchantModule = module {

    single<MerchantRepository> { MerchantRepositoryImp(get()) }

    single<MerchantUseCase> { MerchantUseCaseImp(get()) }
    single<FavUseCase> { FavUseCaseImp(get()) }

    viewModel { OffersViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get(), get()) }

}

