package com.example.domain.usecase

import com.example.adro.base.ApiResult
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantUseCase {
    
    fun fetchTabs(params: HashMap<String,String>?= hashMapOf()): Flow<ApiResult<TabsResponse>>
    suspend fun fetchOffers(params: HashMap<String,String>?): List<OffersResponse.Data.Outlet>
}