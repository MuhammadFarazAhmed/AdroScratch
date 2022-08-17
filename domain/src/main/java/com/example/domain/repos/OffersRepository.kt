package com.example.domain.repos


import com.example.adro.base.ApiResult
import com.example.domain.models.HomeResponse
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface OffersRepository {

    fun fetchTabs(): Flow<ApiResult<TabsResponse>>
    fun fetchOffers(params: TabsResponse.Data.Tab.Params?): Flow<ApiResult<OffersResponse>>

}