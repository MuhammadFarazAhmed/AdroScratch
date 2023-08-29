package com.example.domain.usecase


import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonUseCase {
    suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>>

    fun getLanguage(): Flow<String>

     fun setLanguage(language: String)

}