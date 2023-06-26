package com.example.repositories.usecases

import com.example.adro.models.FavoriteResponse
import com.example.domain.repos.FavoritesRepository
import com.example.domain.usecase.FavUseCase
import javax.inject.Inject

class FavUseCaseImp @Inject constructor(private val favRepository: FavoritesRepository) :
    FavUseCase {
    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> = favRepository.fetchFavorites()
}

