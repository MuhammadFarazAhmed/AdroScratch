package com.example.domain.usecase

import com.example.adro.base.ApiResult
import com.example.domain.models.FavoriteResponse
import com.example.domain.models.HomeResponse
import com.example.domain.models.OffersResponse
import com.example.domain.models.TabsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface FavUseCase {

    suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet>

}