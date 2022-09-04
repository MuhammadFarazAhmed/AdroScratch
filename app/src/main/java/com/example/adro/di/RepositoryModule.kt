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

@Module @InstallIn(SingletonComponent::class) class RepositoryModule {
    
    /**
     * Home
     */
    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi = retrofit.create(HomeApi::class.java)
    
    @Provides
    @Singleton
    fun provideHomeRepository(client: HttpClient): HomeRepository = HomeRepositoryImp(client)
    
    
    /**
     * Offers
     */
    @Provides
    @Singleton
    fun provideOffersApi(retrofit: Retrofit): OffersApi = retrofit.create(OffersApi::class.java)
    
    @Provides
    @Singleton
    fun provideOffersRepository(offersApi: OffersApi): OffersRepository =
            OffersRepositoryImp(offersApi)
    
    /**
     * Favorites
     */
    @Provides
    @Singleton
    fun provideFavoritesApi(retrofit: Retrofit): FavApi = retrofit.create(FavApi::class.java)
    
    @Provides
    @Singleton
    fun provideFavoritesRepository(favApi: FavApi): FavoritesRepository = FavRepositoryImp(favApi)
    
    
    /**
     * Profile
     */
    @Provides
    @Singleton
    fun provideProfileApi(retrofit: Retrofit): ProfileApi = retrofit.create(ProfileApi::class.java)
    
    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ProfileApi): ProfileRepository =
            ProfileRepositoryImp(profileApi)
}