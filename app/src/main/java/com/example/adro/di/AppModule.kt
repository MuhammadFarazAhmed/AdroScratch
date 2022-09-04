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

}