package com.example.repositories.repos

import com.example.adro.common.CommonUtilsExtension.API.CORE
import com.example.adro.common.CommonUtilsExtension.setCommonParams
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.http.path


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

    override suspend fun fetchHome(): HomeResponse {

        return client.post {
            setCommonParams(CORE)
            url { path("/ets_api/v5/home") }
        }.body() as HomeResponse

    }

}
