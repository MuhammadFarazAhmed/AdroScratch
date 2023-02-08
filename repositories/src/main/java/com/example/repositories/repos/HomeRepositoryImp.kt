package com.example.repositories.repos

import com.example.adro.common.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.*


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
        convertToFlow {
            client.post {
                url { path("/ets_api/v5/home") }
                setDefaultParams(CommonUtilsExtension.API.HOME)
            }
        }
}
