package com.example.repositories.usecases

import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.Flow

class CommonUseCaseImp(private val commonRepository: CommonRepository) :
    CommonUseCase {
    override suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>> = commonRepository.fetchConfig()

}

