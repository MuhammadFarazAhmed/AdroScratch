package com.example.adro.di.offers

import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.MerchantUseCase
import com.example.repositories.usecases.MerchantUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class OffersModule {

    @Provides
    @ViewModelScoped
    fun provideOffersUseCase(offersRepository: MerchantRepository): MerchantUseCase =
        MerchantUseCaseImp(offersRepository)

}