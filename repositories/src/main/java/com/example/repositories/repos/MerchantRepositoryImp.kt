package com.example.repositories.repos

import com.example.domain.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension.API.OUTLET
import com.example.adro.common.CommonUtilsExtension.API.MERCHANT
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.adro.common.CommonUtilsExtension.setCommonParams
import com.example.domain.models.FavoriteResponse
import com.example.domain.models.MerchantDetailModel
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.MerchantRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow

class MerchantRepositoryImp(
    private val client: HttpClient
) : MerchantRepository {

    override fun fetchTabs(params: HashMap<String, String>?): Flow<ApiResult<TabsResponse>> =
        convertToFlow(call = {
            client.post {
                setCommonParams(OUTLET, params)
                url { path("/ets_api/v5/offer/tabs") }
            }
        }, success = {

        },
            failure = {}
        )

    override suspend fun fetchOffers(
        tabsParams: TabsResponse.Data.Tab.Params?,
        query: String?,
        queryType: String?,
        params: HashMap<String, String>
    ): List<OffersResponse.Data.Outlet> {

        return try {
            val response = client.post {

                setCommonParams(
                    api = OUTLET,
                    additionalParams = tabsParams?.convert<TabsResponse.Data.Tab.Params, HashMap<String, String>>(),
                    params = hashMapOf<String, String>().apply {
                        putAll(params)
                        if (queryType != null && query != null) {
                            put("query", query)
                            put("query_type", queryType)
                        }
                    }
                )

                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as OffersResponse).data.outlets
        } catch (e: Exception) {
            emptyList()
        }

    }

    override suspend fun fetchMerchantDetail(
        merchantId: String,
        params: HashMap<String, String>
    ): Flow<ApiResult<MerchantDetailModel>> =
        convertToFlow(call = {
            client.post {
                setCommonParams(MERCHANT, params)
                url { path("/ets_api/v5/merchants/$merchantId") }
            }
        }, success = {}, failure = {}
        )

    override suspend fun fetchFavorites(params: HashMap<String, String>): List<FavoriteResponse.Data.Outlet> =
        try {
            val response = client.post {
                setCommonParams(OUTLET, params)
                url { path("/ets_api/v5/outlets") }
            }
            (response.body() as FavoriteResponse).data.outlets
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }

}

