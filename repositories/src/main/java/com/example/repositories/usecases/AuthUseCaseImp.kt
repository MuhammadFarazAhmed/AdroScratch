package com.example.repositories.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.adro.paging.BasePagingSource
import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.models.ProfileResponse
import com.example.domain.repos.AuthRepository
import com.example.domain.usecase.AuthUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class AuthUseCaseImp(private val authRepository: AuthRepository) : AuthUseCase {

    override suspend fun login(phone: String, password: String): Flow<ApiResult<LoginResponse>> {
        val params = hashMapOf("mobile_phone" to phone, "password" to password)
        return authRepository.login(params)
    }

    override fun isUserLoggedIn(): Flow<Boolean> =
        authRepository.isUserLoggedIn().map { it.userId != null }

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

