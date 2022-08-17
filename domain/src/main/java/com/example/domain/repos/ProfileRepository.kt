package com.example.domain.repos


import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProfileRepository {

    fun fetchHome() : Flow<ApiResult<HomeResponse>>
    
}