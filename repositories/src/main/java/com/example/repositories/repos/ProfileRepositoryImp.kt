package com.example.repositories.repos

import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ProfileResponse
import com.example.domain.models.asList
import com.example.domain.repos.ProfileRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileRepositoryImp(private val client: HttpClient) : ProfileRepository {

    override suspend fun fetchProfile(): List<ProfileResponse.Data> {
        return try {
            val response = client.post {
                url { path("et_user/v5/user/profile") }
                setDefaultParams(CommonUtilsExtension.API.PROFILE)
            }
            (response.body() as ProfileResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }

}
