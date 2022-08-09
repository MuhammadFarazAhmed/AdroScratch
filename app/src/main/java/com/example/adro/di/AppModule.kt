package com.example.adro.di

import android.content.Context
import android.util.Base64
import com.example.adro.BuildConfig
import com.example.adro.api.CustomDateTimeAdapter
import com.example.adro.api.JWTInterceptor
import com.example.adro.common.PrefsHelper
import com.example.adro.security.CLibController
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
    fun provideOkHttpClient(jwtInterceptor: JWTInterceptor) =
        OkHttpClient.Builder()
            .addNetworkInterceptor(jwtInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()

    @Provides
    @Singleton
    fun getJwtToken(prefsHelper: PrefsHelper): String =
        Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
            .claim("company", prefsHelper.getCompany())
            .claim("session_token", prefsHelper.getSessionToken())
            .claim("api_token", prefsHelper.getJToken())
            .signWith(
                SignatureAlgorithm.HS256,
                Base64.encodeToString(prefsHelper.getSRKey().toByteArray(), Base64.DEFAULT)
            )
            .compact()

    @Provides
    @Singleton
    fun prefsHelper(context: Context) = PrefsHelper(context)


    @Provides
    @Singleton
    fun provideBuildFlavor() = BuildConfig.FLAVOR

    @Provides
    @Singleton
    fun provideBaseUrl() = CLibController.getCoreBaseUrlOnline()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, gson: Gson, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

}