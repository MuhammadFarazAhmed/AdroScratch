package com.example.sharedcode.domain.usecase

import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.domain.domain_model.HomeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeUseCaseImp constructor(private val homeRepository: HomeRepository) : HomeUseCase {

    override suspend fun fetchHome(): Flow<HomeResponse> = flow {
        val response = homeRepository.fetchHome()
        emit(response)
    }
}

