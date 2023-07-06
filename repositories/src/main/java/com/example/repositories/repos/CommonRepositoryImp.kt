package com.example.repositories.repos

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.adro.common.CommonFlowExtensions
import com.example.adro.common.CommonFlowExtensions.asResult
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension.API.CONFIG
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.common.Result
import com.example.adro.models.ApiResult
import com.example.adro.models.ConfigModel
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.repos.CommonRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


class CommonRepositoryImp(
    private val client: HttpClient,
    private val configDataStore: DataStore<ConfigModel>,
    private val preferencesHelper: PreferencesHelper
) : CommonRepository {

    override suspend fun fetchConfig(): Flow<ApiResult<ConfigModel>> =
        convertToFlow<ConfigModel>(
            call =
            {
                client.post {
                    url { path("/ets_api/v5/configs") }
                    setDefaultParams(CONFIG)
                }
            },
            success =
            { result ->

                configDataStore.updateData { result }

            },
            failure =
            {

            })

    override fun getLanguage(): Flow<String> =
        preferencesHelper.dataSource.data.map { it[stringPreferencesKey("lang")] ?: "" }

    override fun setLanguage(language: String) {
        runBlocking {
            preferencesHelper.dataSource.edit {
                it[stringPreferencesKey("lang")] = language
            }
        }
    }
}
