package com.example.domain.repos


import com.example.adro.base.ApiResult
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantRepository {

    fun fetchTabs(): Flow<ApiResult<TabsResponse>>
    suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet>

}