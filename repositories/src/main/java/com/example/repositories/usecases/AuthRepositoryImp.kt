@file:OptIn(DelicateCoroutinesApi::class)

package com.example.repositories.usecases

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.prefs.PreferencesHelper
import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import com.theentertainerme.adro.security.JWTProvider
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

class AuthRepositoryImp(
    private val client: HttpClient,
    private val userDataStore: DataStore<LoginResponse.Data.User>
) :
    AuthRepository {
    override suspend fun login(hashMap: HashMap<String, String>): Flow<ApiResult<LoginResponse>> =
        convertToFlow(
            call = {
                return@convertToFlow client.post {
                    url { path("/et_user/v5/user/sign_in") }
                    setDefaultParams(CommonUtilsExtension.API.USER, hashMap)
                }
            }, success = { response ->
                response.data?.user?.let { user ->
                    userDataStore.updateData { user }
                }
            }, failure = {

            })

    override fun isUserLoggedIn() = userDataStore.data


}

