package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import com.example.sharedcode.offers.data.repo.MerchantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MerchantUseCaseImp constructor(private val merchantRepository: MerchantRepository) :
    MerchantUseCase {

    override fun fetchTabs(): Flow<TabsResponse> = flow { emit(merchantRepository.fetchTabs()) }

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): Flow<List<OffersResponse.Data.Outlet>> = flow {
        merchantRepository.fetchOffers(params).data?.let { emit(it.outlets as List<OffersResponse.Data.Outlet>) }
    }
}

