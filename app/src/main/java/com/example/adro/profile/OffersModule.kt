package com.example.adro.profile

import com.example.domain.repos.HomeRepository
import com.example.domain.repos.OffersRepository
import com.example.domain.repos.ProfileRepository
import com.example.domain.usecase.HomeUseCase
import com.example.domain.usecase.OffersUseCase
import com.example.domain.usecase.ProfileUseCase
import com.example.repositories.usecases.HomeUseCaseImp
import com.example.repositories.usecases.OffersUseCaseImp
import com.example.repositories.usecases.ProfileUseCaseImp
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