package com.example.adro.di.home

import com.example.domain.repos.HomeRepository
import com.example.domain.usecase.HomeUseCase
import com.example.repositories.usecases.HomeUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class HomeModule {

    @Provides
    @ViewModelScoped
    fun provideHomeUseCase(homeRepository: HomeRepository): HomeUseCase =
        HomeUseCaseImp(homeRepository)

}