package com.example.domain.usecase

import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProfileUseCase {
    
    fun fetchProfile(): Flow<ApiResult<HomeResponse>>
}