package com.example.domain.usecase

import com.example.adro.models.ApiResult
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantUseCase {

    fun fetchTabs(params: HashMap<String, String>? = hashMapOf()): Flow<ApiResult<TabsResponse>>

    suspend fun fetchOffers(
        tabsParams: TabsResponse.Data.Tab.Params? = null,
        query: String? = null,
        queryType: String? = null,
        params: HashMap<String, String> = hashMapOf()
    ): List<OffersResponse.Data.Outlet>
}