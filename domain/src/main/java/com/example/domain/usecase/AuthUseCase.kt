package com.example.domain.usecase

import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.models.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun login(phone: String, password: String): Flow<ApiResult<LoginResponse>>

    suspend fun getCountryList(): Flow<ConfigModel>

    suspend fun signup()

    suspend fun forgotPassword()

    suspend fun validateEMID()

    suspend fun resetPassword()

}