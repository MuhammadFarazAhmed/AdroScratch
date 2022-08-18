package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.OffersRepository
import com.example.domain.usecase.OffersUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OffersUseCaseImp @Inject constructor(private val offersRepository: OffersRepository) :
    OffersUseCase {

    override fun fetchTabs(): Flow<ApiResult<TabsResponse>> = offersRepository.fetchTabs()

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> = offersRepository.fetchOffers(params)
}

