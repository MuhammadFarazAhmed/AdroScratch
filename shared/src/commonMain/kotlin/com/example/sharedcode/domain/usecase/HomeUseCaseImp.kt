package com.example.sharedcode.domain.usecase

import com.example.sharedcode.data.repo.HomeRepository
import com.example.sharedcode.domain.domain_model.HomeResponse
import kotlinx.coroutines.flow.Flow

class HomeUseCaseImp constructor(private val homeRepository: HomeRepository) : HomeUseCase {
    
    override suspend fun fetchHome(): Flow<HomeResponse> = homeRepository.fetchHome()
}

