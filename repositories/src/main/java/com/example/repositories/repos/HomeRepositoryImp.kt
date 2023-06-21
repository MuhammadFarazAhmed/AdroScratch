package com.example.repositories.repos

import com.example.domain.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension.API.CORE
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
        convertToFlow(
            call = {
                client.post {
                    setDefaultParams(CORE)
                    url { path("/ets_api/v5/home") }
                }
            }, success = {

            }, failure = {

            })

}
