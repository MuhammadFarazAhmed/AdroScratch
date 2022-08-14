package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.example.domain.usecase.HomeUseCase
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class HomeUseCaseImp @Inject constructor(private val homeRepository: HomeRepository) : HomeUseCase {
    
    override fun fetchHome(): Flow<ApiResult<HomeResponse>> = homeRepository.fetchHome()
}

