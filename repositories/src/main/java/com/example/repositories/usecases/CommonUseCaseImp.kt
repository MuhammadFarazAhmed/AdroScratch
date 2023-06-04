package com.example.repositories.usecases

import com.example.domain.models.ApiResult
import com.example.domain.models.HomeResponse
import com.example.domain.repos.CommonRepository
import com.example.domain.usecase.CommonUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommonUseCaseImp @Inject constructor(private val commonRepository: CommonRepository) :
    CommonUseCase {
    override suspend fun fetchConfig(): Flow<ApiResult<HomeResponse>> {
        TODO("Not yet implemented")
    }


}

