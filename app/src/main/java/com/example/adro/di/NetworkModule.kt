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
    fun provideGson(): Gson = GsonBuilder().serializeNulls()
            .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            //.registerTypeAdapter(Date::class.java, CustomDateTimeAdapter::class.java)
            .setLenient().setPrettyPrinting().create()
    
    @Provides
    @Singleton
    @Named("auth")
    fun provideAuthInterceptor(jwtToken: String) = Interceptor { chain: Interceptor.Chain ->
        val request =
                chain.request().newBuilder().addHeader("Authorization", "Bearer $jwtToken").build()
        chain.proceed(request)
    }
    
    @Singleton
    @Provides
    @Named("decrypted")
    fun provideDecryptionInterceptor(apisEncryptionUtils: ApisEncryptionUtils) =
            Interceptor { chain ->
                val encryptedRequest = chain.request()
                val originalResponse = chain.proceed(encryptedRequest)
                
                chain.proceed(encryptedRequest)
                
                val originalJson = originalResponse.body?.string()
                val decryptedResponse = apisEncryptionUtils.decryptString(originalJson)
                
                if (decryptedResponse != null) {
                    
                    if (BuildConfig.DEBUG) Log.d("okhttp.OkHttpClient", decryptedResponse)
                    originalResponse.newBuilder()
                            .body(decryptedResponse.toResponseBody(originalResponse.body?.contentType()))
                            .build()
                    
                } else {
                    
                    originalResponse.newBuilder()
                            .body(originalJson?.toResponseBody(originalResponse.body?.contentType()))
                            .build()
                    
                }
            }
    
    @Singleton
    @Provides
    @Named("baseurl")
    fun provideChangeBaseUrlInterceptor(controller: CLibController) = Interceptor { chain ->
        
        var host = controller.getENTBaseUrlOnline().toHttpUrl()
        
        var request: Request = chain.request()
        
        when {
            request.getAnnotation(HomeApi::class.java) == HomeApi() -> {
                host = controller.getENTBaseUrlOnline().toHttpUrl()
            }
            request.getAnnotation(OffersApi::class.java) == OffersApi() -> {
                host = controller.getOutletBaseUrlOnline().toHttpUrl()
            }
            request.getAnnotation(FavApi::class.java) == FavApi() -> {
                host = controller.getOutletBaseUrlOnline().toHttpUrl()
            }
            request.getAnnotation(ProfileApi::class.java) == ProfileApi() -> {
                host = controller.getAuthBaseUrlOnline().toHttpUrl()
            }
        }
        
        var newUrl: HttpUrl? = null
        try {
            newUrl = request.url.newBuilder().scheme(host.scheme).host(host.toUrl().toURI().host)
                    .build()
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
        assert(newUrl != null)
        request = request.newBuilder().url(newUrl!!).build()
        chain.proceed(request)
    }
    
    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("auth") authInterceptor: Interceptor,
                            @Named("baseurl") changeBaseUrlInterceptor: Interceptor,
                            @Named("decrypted") decryptInterceptor: Interceptor) =
            OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(authInterceptor).addInterceptor(changeBaseUrlInterceptor)
                    .addInterceptor(decryptInterceptor)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) this.level = HttpLoggingInterceptor.Level.BODY
                    }).build()
    
    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient, controller: CLibController): Retrofit =
            Retrofit.Builder().baseUrl(controller.getENTBaseUrlOnline())
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()
    
    @Provides
    @Singleton
    fun provideKtor(jwtToken: String,
                    cLibController: CLibController,
                    apisEncryptionUtils: ApisEncryptionUtils): HttpClient =
            
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
                    this.apisEncryptionUtils = apisEncryptionUtils
                }
    
                HttpResponseValidator {
                    validateResponse { response ->
                        val body: HttpResponse = response.body()
                        response.bodyAsChannel()
                    }
                }
                
                
            }.apply {
                changeBaseUrlInterceptor(cLibController)
            }
    
    private fun HttpClient.changeBaseUrlInterceptor(cLibController: CLibController) {
        plugin(HttpSend).intercept { request ->
            when (request.attributes[MyAttributeKey]) {
                "homeApi" -> request.url.host = cLibController.getENTBaseUrlOnline()
                "profileApi" -> request.url.host = cLibController.getAuthBaseUrlOnline()
            }
            
            execute(request)
        }
    }
    
}
