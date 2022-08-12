package com.example.adro.di

import android.app.Application
import android.content.Context
import android.util.Base64
import com.example.adro.BuildConfig
import com.example.adro.api.CustomDateTimeAdapter
import com.example.adro.common.PreferencesHelper
import com.example.repositories.security.CLibController
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
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .serializeNulls()
        .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Date::class.java, CustomDateTimeAdapter::class.java)
        .setLenient()
        .setPrettyPrinting().create()

    @Provides
    @Singleton
    fun provideAuthInterceptor(jwtToken: String) = Interceptor { chain: Interceptor.Chain ->
        val request =
            chain.request().newBuilder().addHeader("Authorization", "Bearer $jwtToken").build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor) =
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addNetworkInterceptor(authInterceptor)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    @Provides
    @Singleton
    fun provideJwtToken(preferencesHelper: PreferencesHelper): String =
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", preferencesHelper.getCompany())
            .claim("session_token", preferencesHelper.getSessionToken())
            .claim("api_token", preferencesHelper.getJToken())
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(preferencesHelper.getSRKey().toByteArray(), Base64.DEFAULT)
            )
            .compact()

    @Provides
    @Singleton
    fun preferencesHelper(context: Context) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideContext(application: Application): Context =
        application.applicationContext

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(CLibController.getCoreBaseUrlOnline())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

}