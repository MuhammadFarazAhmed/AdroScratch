package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.toResultFlow
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import com.example.domain.repos.OffersRepository
import com.example.repositories.remote.api.OffersApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OffersRepositoryImp(
    private val offersApi: OffersApi
) : OffersRepository {

    override fun fetchTabs(): Flow<ApiResult<TabsResponse>> = flow { }

    override suspend fun fetchOffers(params: TabsResponse.Data.Tab.Params?): List<OffersResponse.Data.Outlet> =
        offersApi.offers(
            params.convert<TabsResponse.Data.Tab.Params?, HashMap<String, String>>().apply {
                put("__company", "ADO")
                put("__lng", "0")
                put("device_key", "26525de9bd832a74")
                put("app_version", "1.0")
                put("lng", "0")
                put("device_model", "samsung%20SM-F916B")
                put("device_os_ver", "12")
                put("language", "en")
                put("build_no", "37")
                put("time_zone", "Asia/Karachi")
                put("device_os", "android")
                put("location_id", "2")
                put("device_uuid", "26525de9bd832a74")
                put("__platform", "android")
                put("device_uid", "26525de9bd832a74")
                put("__lat", "0")
                put("company", "ADO")
                put("currency", "AED")
                put("wlcompany", "ADO")
                put("lat", "0")
            }).body()?.data?.outlets!!

}

