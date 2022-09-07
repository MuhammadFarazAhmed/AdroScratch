package com.example.adro.di

import android.util.Log
import com.example.adro.BuildConfig
import com.example.adro.common.*
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.adro.common.CommonUtilsExtension.getAnnotation
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.security.CLibController
import com.example.repositories.annotations.*
import com.example.repositories.repos.MyAttributeKey
import com.google.gson.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.kotlinx.serializer.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.cio.*
import io.ktor.serialization.gson.*
import io.ktor.util.*
import io.ktor.util.reflect.*
import io.ktor.utils.io.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module @InstallIn(SingletonComponent::class) class NetworkModule {
    
    @Provides
    @Singleton
    fun provideKtor(jwtToken: String,
                    cLibController: CLibController,
                    apisEncryption: ApisEncryptionUtils): HttpClient =
            
            HttpClient(Android) {
                
                defaultRequest {
                    url {
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                        
                        protocol = URLProtocol.HTTPS
                        host = cLibController.getENTBaseUrlOnline()
                    }
                    
                    request {
                        //TODO set default body
                    }.build()
                }
                
                install(Auth) {
                    bearer {
                        loadTokens {
                            BearerTokens(jwtToken, jwtToken)
                        }
                    }
                }
                
                install(Logging) { level = LogLevel.ALL }
                
                install(ContentNegotiation) { gson { } }
                
                decryptResponse {
                    apisEncryptionUtils = apisEncryption
                }

            }.apply {
                changeBaseUrlInterceptor(cLibController)
            }
    
    private fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
        plugin(HttpSend).intercept { request ->
            when (request.attributes[MyAttributeKey]) {
                "homeApi" -> request.url.host = cLibController.getENTBaseUrlOnline()
                "profileApi" -> request.url.host = cLibController.getAuthBaseUrlOnline()
                "favApi" -> request.url.host = cLibController.getOutletBaseUrlOnline()
                "offersApi" -> request.url.host = cLibController.getOutletBaseUrlOnline()
            }
            
            execute(request)
        }
    }
    
}
