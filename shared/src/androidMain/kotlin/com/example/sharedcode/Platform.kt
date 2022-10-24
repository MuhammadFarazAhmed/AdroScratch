package com.example.sharedcode

import ApisEncryptionUtils
import android.util.Base64
import com.example.sharedcode.common.changeBaseUrlInterceptor
import com.example.sharedcode.common.decryptResponse
import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.data.repo.HomeRepositoryImp
import com.example.sharedcode.domain.usecase.HomeUseCase
import com.example.sharedcode.domain.usecase.HomeUseCaseImp
import com.example.sharedcode.presentation.HomeViewModel
import com.example.sharedcode.security.CLibController
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import org.koin.dsl.module

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {
    single {
        Android.create()
    }

    single { HomeViewModel(get()) }

    single<HomeRepository> { HomeRepositoryImp(get()) }

    single<HomeUseCase> { HomeUseCaseImp(get()) }

    single { CLibController() }

    single { ApisEncryptionUtils(get()) }


    single<String> {
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", "get<PreferencesHelper>().getCompany()")
            .claim("session_token", "get<PreferencesHelper>().getSessionToken()")
            .claim("api_token", "get<PreferencesHelper>().getApiToken()")
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(
                    "get<PreferencesHelper>().getSRKey()".toByteArray(),
                    Base64.DEFAULT
                )
            ).compact()

    }

    single {
        createHttpClient(
            httpClientEngine = get(),
            cLibController = get(),
            token = get(),
            encryptionUtils = get()
        )
    }
}

fun createHttpClient(
    httpClientEngine: HttpClientEngine,
    cLibController: CLibController,
    token: String,
    encryptionUtils: ApisEncryptionUtils
) = HttpClient(httpClientEngine) {

    defaultRequest {
        url {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)

            protocol = URLProtocol.HTTPS
            host = cLibController.getENTBaseUrlOnline()
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
        json()
    }

    decryptResponse {
        apisEncryptionUtils = encryptionUtils
    }

}.apply {
    changeBaseUrlInterceptor(cLibController)
}
