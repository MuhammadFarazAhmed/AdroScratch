package com.example.sharedcode.offers.data.repo



import com.example.sharedcode.domain.domain_model.OffersResponse
import com.example.sharedcode.domain.domain_model.TabsResponse

interface MerchantRepository {

    suspend fun fetchTabs(): TabsResponse

    suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet>

}