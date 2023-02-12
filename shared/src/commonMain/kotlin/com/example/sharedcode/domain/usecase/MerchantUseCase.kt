package com.example.sharedcode.domain.usecase

import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantUseCase {
    
    fun fetchTabs(): Flow<TabsResponse>

    suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): Flow<List<OffersResponse.Data.Outlet>>
}