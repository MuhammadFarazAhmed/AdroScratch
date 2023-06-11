package com.example.domain.usecase

import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

     suspend fun login(): Flow<ApiResult<LoginResponse>>

    suspend  fun signup()

    suspend  fun forgotPassword()

    suspend  fun validateEMID()

    suspend  fun resetPassword()

}