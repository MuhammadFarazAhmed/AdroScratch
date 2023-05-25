package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.*


class CommonRepositoryImp(private val client: HttpClient) : CommonRepository {

    override suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>> =
        convertToFlow {
            client.post {
                url { path("/ets_api/v5/configs") }
                setDefaultParams(CommonUtilsExtension.API.CONFIG)
            }
        }

}
