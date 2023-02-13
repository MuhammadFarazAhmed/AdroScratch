package com.example.sharedcode.profile.data.repo

import com.example.sharedcode.common.CommonFlowExtensions.toCustomExceptions
import com.example.sharedcode.common.CommonUtilsExtension
import com.example.sharedcode.common.CommonUtilsExtension.setDefaultParams
import com.example.sharedcode.domain.domain_model.ProfileResponse
import com.example.sharedcode.domain.domain_model.asList
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileRepositoryImp(private val client: HttpClient) : ProfileRepository {

    override suspend fun fetchProfile(): ProfileResponse =
        client.post {
            url { path("/et_user/v5/user/profile") }
            setDefaultParams(CommonUtilsExtension.API.PROFILE)
        }.body()

}
