package com.example.domain.usecase

import com.example.domain.models.FavoriteResponse


interface FavUseCase {

    suspend fun fetchFavorites(params: HashMap<String, String>): List<FavoriteResponse.Data.Outlet>

}