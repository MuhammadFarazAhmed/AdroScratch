package com.example.sharedcode.data.repo


import com.example.adro.base.ApiResult
import com.example.sharedcode.model.HomeResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    
    suspend fun fetchHome() : Flow<ApiResult<HomeResponse>>
    
}