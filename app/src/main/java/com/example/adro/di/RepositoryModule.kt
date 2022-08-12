package com.example.adro.di

import com.example.domain.repos.HomeRepository
import com.example.repositories.remote.api.HomeApi
import com.example.repositories.repos.HomeRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)

    @Provides
    @Singleton
    fun provideHomeRepository(homeApi: HomeApi): HomeRepository = HomeRepositoryImp(homeApi)
}