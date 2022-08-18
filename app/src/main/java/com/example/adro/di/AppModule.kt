package com.example.adro.di

import android.app.Application
import android.content.Context
import android.util.Base64
import android.util.Log
import androidx.compose.ui.text.buildAnnotatedString
import com.example.adro.BuildConfig
import com.example.adro.common.PreferencesHelper
import com.example.adro.security.CLibController
import com.example.repositories.annotations.HomeApi
import com.example.repositories.annotations.OffersApi
import com.example.repositories.annotations.ProfileApi
import com.fasterxml.jackson.annotation.JsonValue
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
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
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URISyntaxException
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Scope
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

    private fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? {
        return this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
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
            request.getAnnotation(ProfileApi::class.java) == ProfileApi() -> {
                host = controller.getAuthBaseUrlOnline().toHttpUrl()
            }
            request.getAnnotation(OffersApi::class.java) == OffersApi() -> {
                host = controller.getOutletBaseUrlOnline().toHttpUrl()
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
        @Named("baseurl") changeBaseUrlInterceptor: Interceptor
    ) =
        OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor).addInterceptor(changeBaseUrlInterceptor)
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