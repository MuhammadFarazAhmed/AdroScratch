package com.example.repositories.repos

import com.example.domain.repos.HomeRepository
import io.ktor.client.*


class HomeRepositoryImp(private val client: HttpClient) : HomeRepository {

//    override suspend fun fetchHome(): Flow<ApiResult<HomeResponse>> =
//        convertToFlow {
//            client.post {
//                url { path("/ets_api/v5/home") }
//                setDefaultParams(CommonUtilsExtension.API.HOME)
//            }
//        }
}
