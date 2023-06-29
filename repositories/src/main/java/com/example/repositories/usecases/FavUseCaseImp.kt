package com.example.repositories.usecases

import com.example.adro.models.FavoriteResponse
import com.example.domain.repos.MerchantRepository
import com.example.domain.usecase.FavUseCase
import com.example.domain.usecase.MerchantUseCase
import javax.inject.Inject

class FavUseCaseImp(private val merchantRepository: MerchantRepository) :
    FavUseCase {

    override suspend fun fetchFavorites(): List<FavoriteResponse.Data.Outlet> =
        merchantRepository.fetchFavorites()

}

