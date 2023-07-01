package com.example.repositories.repos

import com.example.adro.common.CommonFlowExtensions.convertToFlow
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.API.CORE
import com.example.adro.common.CommonUtilsExtension.setDefaultParams
import com.example.adro.models.HomeResponse
import com.example.adro.models.ProfileResponse
import com.example.adro.models.asList
import com.example.domain.repos.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

    override suspend fun fetchHome(): List<HomeResponse.Data.Section> {

        val response = client.post {
            setDefaultParams(CORE)
            url { path("/ets_api/v5/home") }
        }
        return (response.body() as HomeResponse).data.sections

    }

}
