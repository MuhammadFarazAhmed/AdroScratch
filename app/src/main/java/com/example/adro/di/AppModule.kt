package com.example.adro.di

import android.app.Application
import android.content.Context
import android.util.Base64
import android.util.Log
import com.example.adro.BuildConfig
import com.example.adro.common.CommonUtilsExtension.getAnnotation
import com.example.adro.common.PreferencesHelper
import com.example.adro.security.ApisEncryptionUtils
import com.example.adro.security.CLibController
import com.example.repositories.annotations.FavApi
import com.example.repositories.annotations.HomeApi
import com.example.repositories.annotations.OffersApi
import com.example.repositories.annotations.ProfileApi
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

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
    @Named("decrypter")
    fun provideDecryptionInterceptor(apisEncryptionUtils: ApisEncryptionUtils) =
        Interceptor { chain ->
            val encryptedRequest = chain.request()
            val originalResponse = chain.proceed(encryptedRequest)

            chain.proceed(encryptedRequest)

            val originalJson = originalResponse.body?.string()
            val decryptedResponse = apisEncryptionUtils.decryptString(originalJson)

            if (decryptedResponse != null) {

                if (BuildConfig.DEBUG) Log.d(
                    "okhttp.OkHttpClient",
                    decryptedResponse
                )
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
    fun provideOkHttpClient(
        @Named("auth") authInterceptor: Interceptor,
        @Named("baseurl") changeBaseUrlInterceptor: Interceptor,
        @Named("decrypter") decryptInterceptor: Interceptor
    ) =
        OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(changeBaseUrlInterceptor)
            .addInterceptor(decryptInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) this.level = HttpLoggingInterceptor.Level.BODY
            }).build()

    @Provides
    @Singleton
    fun provideJwtToken(preferencesHelper: PreferencesHelper): String =
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", preferencesHelper.getCompany())
            .claim("session_token", preferencesHelper.getSessionToken())
            .claim("api_token", preferencesHelper.getApiToken())
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(
                    preferencesHelper.getSRKey().toByteArray(),
                    Base64.DEFAULT
                )
            ).compact()

    @Provides
    @Singleton
    fun preferencesHelper(context: Context) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun provideCLibController() = CLibController()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient, controller: CLibController): Retrofit =
        Retrofit.Builder().baseUrl(controller.getENTBaseUrlOnline())
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client).build()

}