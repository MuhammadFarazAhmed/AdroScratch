package com.example.domain.usecase

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.domain.models.ApiResult
import com.example.domain.models.MerchantDetailModel
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantUseCase {

    fun fetchTabs(params: HashMap<String, String>? = hashMapOf()): Flow<ApiResult<TabsResponse>>

    suspend fun fetchOffers(
        tabsParams: TabsResponse.Data.Tab.Params? = null,
        query: String? = null,
        queryType: String? = null,
        params: HashMap<String, String> = hashMapOf()
    ): Flow<PagingData<OffersResponse.Data.Outlet>>


    suspend fun fetchMerchantDetail(
        merchantId: String,
        params: HashMap<String, String>
    ): Flow<ApiResult<MerchantDetailModel>>

}