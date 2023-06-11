package com.example.repositories.usecases

import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImp(private val authRepository: AuthRepository) : AuthUseCase {
    override suspend fun login(): Flow<ApiResult<LoginResponse>> =
        authRepository.login()

    override suspend fun signup() {
        TODO("Not yet implemented")
    }

    override suspend fun forgotPassword() {
        TODO("Not yet implemented")
    }

    override suspend fun validateEMID() {
        TODO("Not yet implemented")
    }

    override suspend fun resetPassword() {
        TODO("Not yet implemented")
    }

}

