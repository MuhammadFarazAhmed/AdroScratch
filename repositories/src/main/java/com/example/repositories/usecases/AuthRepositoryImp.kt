@file:OptIn(DelicateCoroutinesApi::class)

package com.example.repositories.usecases

import androidx.datastore.core.DataStore
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.prefs.PreferenceDataStoreConstants.IS_LOGGED_IN_KEY
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthRepositoryImp(
    private val client: HttpClient,
    private val userDataStore: DataStore<LoginResponse.Data.User>,
    private val preferencesHelper: PreferencesHelper
) :
    AuthRepository {
    override suspend fun login(hashMap: HashMap<String, String>): Flow<ApiResult<LoginResponse>> =
        convertToFlow(call = {
            return@convertToFlow client.post {
                url { path("/et_user/v5/user/sign_in") }
                setDefaultParams(CommonUtilsExtension.API.USER, hashMap)
            }
        }, success = {
            GlobalScope.launch {
                userDataStore.updateData { it }
                preferencesHelper.putPreference(IS_LOGGED_IN_KEY, true)
            }
        }, failure = {

        })


}

