package com.example.sharedcode.data.repo

import com.example.sharedcode.common.Result
import com.example.sharedcode.data.api.HomeApi
import com.example.sharedcode.domain.domain_model.HomeResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class HomeRepositoryImp(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun fetchHome(): HomeResponse = homeApi.getHome()

}
