package com.example.domain.usecase

import com.example.adro.models.FavoriteResponse

interface FavUseCase {

    suspend fun fetchFavorites(params: HashMap<String, String>): List<FavoriteResponse.Data.Outlet>

}