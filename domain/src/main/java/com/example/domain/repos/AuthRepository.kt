package com.example.domain.repos

import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.models.LogoutModel
import kotlinx.coroutines.flow.Flow
import java.util.HashMap


interface AuthRepository {

    suspend fun login(hashMap: HashMap<String,String> = hashMapOf()) : Flow<ApiResult<LoginResponse>>
    fun isUserLoggedIn(): Flow<LoginResponse.Data.User>
    fun logout() : Flow<ApiResult<LogoutModel>>

}