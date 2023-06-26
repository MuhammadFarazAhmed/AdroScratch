package com.example.repositories.repos

import androidx.datastore.core.DataStore
import com.example.adro.common.CommonFlowExtensions
import com.example.adro.common.CommonFlowExtensions.asResult
import com.example.adro.common.CommonUtilsExtension.API.CONFIG
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.common.Result
import com.example.adro.models.ConfigModel
import com.example.domain.repos.CommonRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class CommonRepositoryImp(
    private val client: HttpClient,
    private val configDataStore: DataStore<ConfigModel>
) : CommonRepository {

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun fetchConfig(): Flow<Result<ConfigModel>> =
        flow<ConfigModel> {
            client.post {
                url { path("/ets_api/v5/configs") }
                setDefaultParams(CONFIG)
            }
        }.asResult({ configDataStore.updateData { it  }})
//    convertToFlow<ConfigModel>(
//    call =
//    {
//        client.post {
//            url { path("/ets_api/v5/configs") }
//            setDefaultParams(CONFIG)
//        }
//    },
//    success =
//    {
//        result ->
//
//        configDataStore.updateData { result }
//
//    },
//    failure =
//    {
//
//    })
}
