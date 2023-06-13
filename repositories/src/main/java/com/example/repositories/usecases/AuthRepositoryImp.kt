package com.example.repositories.usecases

import androidx.datastore.core.DataStore
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.convert
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ApiResult
import com.example.domain.models.ConfigModel
import com.example.domain.models.ErrorResponse
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import io.ktor.client.*
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.http.path
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import okhttp3.internal.EMPTY_RESPONSE
import java.lang.Exception
import java.util.HashMap

class AuthRepositoryImp(
    private val client: HttpClient,
    private val userDataStore: DataStore<LoginResponse.Data.User>
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
            }
        }, failure = {

        })

}

