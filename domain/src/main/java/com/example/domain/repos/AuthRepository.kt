package com.example.domain.repos

import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import kotlinx.coroutines.flow.Flow


interface AuthRepository {

    suspend fun login () : Flow<ApiResult<LoginResponse>>

}