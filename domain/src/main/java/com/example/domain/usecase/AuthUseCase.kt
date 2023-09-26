package com.example.domain.usecase


import androidx.paging.PagingData
import com.example.domain.models.ApiResult
import com.example.domain.models.ErrorResponse
import com.example.domain.models.LoginResponse
import com.example.domain.models.OffersResponse
import com.example.domain.models.ProfileResponse
import kotlinx.coroutines.flow.Flow

interface AuthUseCase {

    suspend fun login(phone: String, password: String): Flow<ApiResult<LoginResponse>>

    fun isUserLoggedIn(): Flow<Boolean>

    fun getUser(): Flow<LoginResponse.Data.User>

    suspend fun signup()

    suspend fun forgotPassword()

    suspend fun validateEMID()

    suspend fun resetPassword()

    suspend fun logOut(): Flow<Boolean>

    suspend fun fetchProfile(): List<ProfileResponse.Data>

}