package com.example.adro.di

import com.example.adro.common.*
import com.example.adro.common.CommonUtilsExtension.Apikey
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.security.CLibController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
import javax.inject.Singleton
import com.example.adro.common.CommonUtilsExtension
import io.ktor.client.statement.*

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideKtor(
        jwtToken: String,
        cLibController: CLibController,
        apisEncryption: ApisEncryptionUtils
    ): HttpClient =

        HttpClient(Android) {

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
                        BearerTokens(jwtToken, jwtToken)
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
                apisEncryptionUtils = apisEncryption
            }

        }.apply {
            changeBaseUrlInterceptor(cLibController)
        }

    private fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
        plugin(HttpSend).intercept { request ->
            when (request.attributes[Apikey]) {
                CommonUtilsExtension.API.HOME -> request.url.host =
                    cLibController.getENTBaseUrlOnline()
                CommonUtilsExtension.API.PROFILE -> request.url.host =
                    cLibController.getAuthBaseUrlOnline()
                CommonUtilsExtension.API.FAV -> request.url.host =
                    cLibController.getOutletBaseUrlOnline()
                CommonUtilsExtension.API.OFFER -> request.url.host =
                    cLibController.getOutletBaseUrlOnline()
            }

            execute(request)
        }
    }

}
