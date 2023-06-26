package com.example.repositories.usecases

import com.example.adro.common.Result
import com.example.adro.models.ConfigModel
import com.example.domain.repos.CommonRepository
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.Flow

class CommonUseCaseImp(private val commonRepository: CommonRepository) :
    CommonUseCase {
    override suspend fun fetchConfig(): Flow<Result<ConfigModel>> = commonRepository.fetchConfig()

}

