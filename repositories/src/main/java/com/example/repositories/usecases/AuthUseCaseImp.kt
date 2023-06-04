package com.example.repositories.usecases

import com.example.domain.repos.AuthRepository
import com.example.domain.usecase.AuthUseCase
import javax.inject.Inject

class AuthUseCaseImp @Inject constructor(private val authRepository: AuthRepository) : AuthUseCase {

}

