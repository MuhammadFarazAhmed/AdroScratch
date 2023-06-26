package com.example.domain.usecase

import com.example.adro.common.Result
import com.example.adro.models.ConfigModel
import kotlinx.coroutines.flow.Flow

interface CommonUseCase {
    suspend fun fetchConfig(): Flow<Result<ConfigModel>>

}