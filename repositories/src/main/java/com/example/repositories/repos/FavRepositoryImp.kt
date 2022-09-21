package com.example.repositories.repos

import android.util.Log
import com.example.adro.common.CommonFlowExtensions.toCustomExceptions
import com.example.adro.common.CommonUtilsExtension
import com.example.adro.common.CommonUtilsExtension.setDefaultData
import com.example.domain.models.FavoriteResponse
import com.example.domain.models.OffersResponse
import com.example.domain.models.ProfileResponse
import com.example.domain.models.asList
import com.example.domain.repos.FavoritesRepository
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class FavRepositoryImp(
    private val client: HttpClient
) : FavoritesRepository {
    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> =
        try {
            val response = client.post {
                url { path("/ets_api/v5/outlets") }
                setDefaultData(CommonUtilsExtension.API.FAV)
            }
            (response.body() as FavoriteResponse).asList()
        } catch (e: Exception) {
            e.toCustomExceptions()
            emptyList()
        }
}

