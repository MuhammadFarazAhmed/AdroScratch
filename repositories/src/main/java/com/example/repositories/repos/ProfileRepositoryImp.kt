package com.example.repositories.repos

import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension.API.USER
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.domain.models.ProfileResponse
import com.example.domain.models.asList
import com.example.domain.repos.ProfileRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path

class ProfileRepositoryImp(private val client: HttpClient) : ProfileRepository {

    override suspend fun fetchProfile(): List<ProfileResponse.Data> {
        return try {
            val response = client.post {
                url { path("et_user/v5/user/profile") }
                setDefaultParams(USER)
            }
            (response.body() as ProfileResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
    }

}
