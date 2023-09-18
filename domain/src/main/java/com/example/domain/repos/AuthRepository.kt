package com.example.domain.repos


import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.models.ProfileResponse
import kotlinx.coroutines.flow.Flow
import java.util.HashMap


interface AuthRepository {

    suspend fun login(hashMap: HashMap<String,String> = hashMapOf()) : Flow<ApiResult<LoginResponse>>

    fun isUserLoggedIn(): Flow<LoginResponse.Data.User>

    fun getUser(): Flow<LoginResponse.Data.User>

    suspend fun logout() : Flow<Boolean>

    suspend fun fetchProfile(): List<ProfileResponse.Data>

}