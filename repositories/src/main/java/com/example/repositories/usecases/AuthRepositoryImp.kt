package com.example.repositories.usecases

import com.example.adro.common.CommonFlowExtensions
import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ApiResult
import com.example.domain.models.LoginResponse
import com.example.domain.repos.AuthRepository
import io.ktor.client.*
import io.ktor.client.request.post
import io.ktor.http.path
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImp(private val client: HttpClient) :
    AuthRepository {
    override suspend fun login(): Flow<ApiResult<LoginResponse>> =
        convertToFlow({
            client.post {
                url { path("/et_user/v5/user/sign_in") }
                setDefaultParams(CommonUtilsExtension.API.USER)
            }
        })

}

