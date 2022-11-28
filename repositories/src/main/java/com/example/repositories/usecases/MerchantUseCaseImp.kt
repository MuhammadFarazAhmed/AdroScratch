package com.example.repositories.usecases

import com.example.adro.base.ApiResult
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.MerchantUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MerchantUseCaseImp @Inject constructor(private val merchantRepository: MerchantRepository) :
    MerchantUseCase {

    override fun fetchTabs(params: HashMap<String,String>?): Flow<ApiResult<TabsResponse>> = merchantRepository.fetchTabs(params)

    override suspend fun fetchOffers(params: HashMap<String,String>?): List<OffersResponse.Data.Outlet> = merchantRepository.fetchOffers(params)
}

