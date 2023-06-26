@file:OptIn(DelicateCoroutinesApi::class)

package com.example.repositories.usecases

import androidx.datastore.core.DataStore
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.models.ApiResult
import com.example.adro.models.LoginResponse
import com.example.adro.models.LogoutModel
import com.example.adro.models.ProfileResponse
import com.example.adro.models.asList
import com.example.domain.repos.AuthRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepositoryImp(
    private val client: HttpClient,
    private val userDataStore: DataStore<LoginResponse.Data.User>
) :
    AuthRepository {

    override fun isUserLoggedIn() = userDataStore.data

    override suspend fun login(hashMap: HashMap<String, String>): Flow<ApiResult<LoginResponse>> =
        convertToFlow<LoginResponse>(
            call = {
                client.post {
                    url { path("/et_user/v5/user/sign_in") }
                    setDefaultParams(CommonUtilsExtension.API.USER, hashMap)
                }
            }, success = { res ->
                res.data?.user?.let { user ->
                    userDataStore.updateData { user }
                }
            }, failure = {

            })

    override suspend fun logout(): Flow<Boolean> =
        convertToFlow<LogoutModel>(
            call = {
                client.post {
                    url { path("/et_user/v5/user/logout") }
                    setDefaultParams(CommonUtilsExtension.API.USER)
                }
            }, success = {
                userDataStore.updateData { LoginResponse.Data.User() }
            }, failure = {

            }
        ).map {
            it.data?.success == true
        }

    override suspend fun fetchProfile(): List<ProfileResponse.Data> {
        return try {
            val response = client.post {
                url { path("et_user/v5/user/profile") }
                setDefaultParams(CommonUtilsExtension.API.USER)
            }
            (response.body() as ProfileResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }


}

