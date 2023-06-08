package com.example.repositories.repos

import com.example.domain.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension.API.CONFIG
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow


class CommonRepositoryImp(private val client: HttpClient) : CommonRepository {

    override suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>> =
        convertToFlow {

            client.post {
                url { path("/ets_api/v5/configs") }
                setDefaultParams(CONFIG)
            }
        }

}
