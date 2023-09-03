package com.example.domain.repos


import com.example.domain.models.ApiResult
import com.example.domain.models.FavoriteResponse
import com.example.domain.models.MerchantDetailModel
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow

interface MerchantRepository {

    fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>>

    suspend fun fetchFavorites(params: HashMap<String, String>): List<FavoriteResponse.Data.Outlet>

    suspend fun fetchOffers(
        tabsParams: TabsResponse.Data.Tab.Params?,
        query: String? = "",
        queryType: String? = "name",
        params: HashMap<String, String> = hashMapOf()
    ): List<OffersResponse.Data.Outlet>

    suspend fun fetchMerchantDetail(
        merchantId: String,
        params: HashMap<String, String>
    ): Flow<ApiResult<MerchantDetailModel>>


}