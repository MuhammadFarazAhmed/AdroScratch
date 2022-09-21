package com.example.adro.di

import android.app.Application
import android.content.Context
import android.util.Base64
import com.example.adro.common.PreferencesHelper
import com.example.adro.security.CLibController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.jsonwebtoken.JwsHeader
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
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