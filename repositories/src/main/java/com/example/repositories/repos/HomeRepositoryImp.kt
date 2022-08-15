package com.example.repositories.repos

import com.example.adro.base.ApiResult
import com.example.adro.common.CommonExtensions.toResultFlow
import com.example.domain.models.HomeResponse
import com.example.domain.repos.HomeRepository
import com.example.repositories.remote.api.HomeApi
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImp(private val homeApi: HomeApi) : HomeRepository {

    override fun fetchHome(): Flow<ApiResult<HomeResponse>> = toResultFlow { homeApi.home() }

}
