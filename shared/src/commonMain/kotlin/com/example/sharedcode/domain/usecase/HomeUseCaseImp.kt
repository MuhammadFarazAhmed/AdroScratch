package com.example.sharedcode.domain.usecase

import com.example.sharedcode.data.repo.HomeRepository

class HomeUseCaseImp constructor(private val homeRepository: HomeRepository) : HomeUseCase {
    
    override suspend fun fetchHome() = homeRepository.fetchHome()
}

