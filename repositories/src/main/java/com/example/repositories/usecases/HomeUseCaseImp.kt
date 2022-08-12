package com.example.repositories.usecases

import com.example.domain.repos.HomeRepository
import com.example.domain.usecase.HomeUseCase
import javax.inject.Inject

class HomeUseCaseImp @Inject constructor(private val homeRepository: HomeRepository) : HomeUseCase {

    override suspend fun fetchHome() {
        homeRepository.
    }
}