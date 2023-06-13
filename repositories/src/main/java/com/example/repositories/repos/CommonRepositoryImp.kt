package com.example.repositories.repos

import androidx.datastore.core.DataStore
import com.example.domain.models.ApiResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension.API.CONFIG
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ConfigModel
import com.example.domain.repos.CommonRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CommonRepositoryImp(
    private val client: HttpClient,
    private val configDataStore: DataStore<ConfigModel>
) : CommonRepository {

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>> =
        convertToFlow(
            call = {
                client.post {
                    url { path("/ets_api/v5/configs") }
                    setDefaultParams(CONFIG)
                }
            }, success = {
                GlobalScope.launch {
                    configDataStore.updateData { it }
                }
            }, failure = {

            })

}
