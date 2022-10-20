package com.example.sharedcode.data.repo

import com.example.sharedcode.common.ApiResult
import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.common.CommonFlowExtensions.convertToFlow
import com.example.sharedcode.domain.domain_model.HomeResponse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
        convertToFlow {
            client.post {
                url { path("/ets_api/v5/home") }
                setDefaultParams(CommonUtilsExtension.API.HOME)
            }
        }

}
