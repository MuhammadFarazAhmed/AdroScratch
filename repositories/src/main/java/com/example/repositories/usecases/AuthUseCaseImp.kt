package com.example.repositories.usecases

import android.util.Log
import com.example.adro.models.ApiResult
import com.example.adro.models.LoginResponse
import com.example.adro.models.ProfileResponse
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
        authRepository.isUserLoggedIn().map {
            Log.d("TAG", "isUserLoggedIn: ${it.userId}")
            it.userId != null
        }

    override fun getUser(): Flow<LoginResponse.Data.User> = authRepository.isUserLoggedIn()


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

    override suspend fun logOut(): Flow<Boolean> = authRepository.logout()

    override suspend fun fetchProfile(): List<ProfileResponse.Data> = authRepository.fetchProfile()

}

