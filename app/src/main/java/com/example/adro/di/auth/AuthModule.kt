package com.example.adro.di.auth

import com.example.domain.repos.AuthRepository
import com.example.domain.repos.HomeRepository
import com.example.domain.usecase.AuthUseCase
import com.example.domain.usecase.HomeUseCase
import com.example.repositories.usecases.AuthUseCaseImp
import com.example.repositories.usecases.HomeUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class AuthModule {

    @Provides
    @ViewModelScoped
    fun provideAuthUseCase(authRepository: AuthRepository): AuthUseCase =
        AuthUseCaseImp(authRepository)

}