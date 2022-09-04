package com.example.adro.di.offers

import com.example.domain.repos.OffersRepository
import com.example.domain.usecase.OffersUseCase
import com.example.repositories.usecases.OffersUseCaseImp
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
    fun provideOffersUseCase(offersRepository: OffersRepository): OffersUseCase =
        OffersUseCaseImp(offersRepository)

}