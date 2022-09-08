package com.example.adro.di

import com.example.domain.repos.FavoritesRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.repos.OffersRepository
import com.example.domain.repos.ProfileRepository
import com.example.repositories.remote.api.FavApi
import com.example.repositories.remote.api.HomeApi
import com.example.repositories.remote.api.OffersApi
import com.example.repositories.remote.api.ProfileApi
import com.example.repositories.repos.FavRepositoryImp
import com.example.repositories.repos.HomeRepositoryImp
import com.example.repositories.repos.OffersRepositoryImp
import com.example.repositories.repos.ProfileRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideHomeRepository(client: HttpClient): HomeRepository = HomeRepositoryImp(client)


    @Provides
    @Singleton
    fun provideOffersRepository(client: HttpClient): OffersRepository = OffersRepositoryImp(client)


    @Provides
    @Singleton
    fun provideFavoritesRepository(client: HttpClient): FavoritesRepository =
        FavRepositoryImp(client)


    @Provides
    @Singleton
    fun provideProfileRepository(client: HttpClient): ProfileRepository =
        ProfileRepositoryImp(client)
}