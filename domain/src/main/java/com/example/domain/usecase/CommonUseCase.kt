package com.example.domain.usecase

import com.example.adro.common.Result
import com.example.adro.models.ApiResult
import com.example.adro.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonUseCase {
    suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>>

    fun getLanguage(): Flow<String>

     fun setLanguage(language: String)

}