package com.example.sharedcode.home.data.repo

import com.example.sharedcode.home.data.api.HomeApi
import com.example.sharedcode.domain.domain_model.HomeResponse


class HomeRepositoryImp(private val homeApi: HomeApi) : HomeRepository {

    override suspend fun fetchHome(): HomeResponse = homeApi.getHome()

}
