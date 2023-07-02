package com.example.domain.repos


import com.example.adro.models.ApiResult
import com.example.adro.models.FavoriteResponse
import com.example.adro.models.OffersResponse
import com.example.adro.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantRepository {

    fun fetchTabs(params: HashMap<String,String>?): Flow<ApiResult<TabsResponse>>

    suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet>

    suspend fun fetchOffers(
        tabsParams: TabsResponse.Data.Tab.Params?,
        query: String? = "mak",
        queryType: String? = "name",
        params: HashMap<String,String> = hashMapOf()
    ): List<OffersResponse.Data.Outlet>
}