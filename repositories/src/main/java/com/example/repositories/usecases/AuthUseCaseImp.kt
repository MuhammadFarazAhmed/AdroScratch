package com.example.repositories.usecases

import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.models.LogoutModel
import com.example.domain.repos.AuthRepository
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthUseCaseImp(private val authRepository: AuthRepository) : AuthUseCase {
    override suspend fun login(phone: String, password: String): Flow<ApiResult<LoginResponse>> {
        val params = hashMapOf("mobile_phone" to phone, "password" to password)
        return authRepository.login(params)
    }

    override fun isUserLoggedIn(): Flow<Boolean> =
        authRepository.isUserLoggedIn().map { it.userId != null }


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

    override fun logOut(): Flow<Boolean> =
        authRepository.logout().map { it.data?.success ?: false }

}

