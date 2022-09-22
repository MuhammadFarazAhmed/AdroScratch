package com.example.offers.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.domain.usecase.FavUseCase
import com.example.repositories.paging.BasePagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(application: Application, favUseCase: FavUseCase) :
    AndroidViewModel(application) {

    val favoriteList =
        Pager(PagingConfig(pageSize = 60)) { BasePagingSource { favUseCase.fetchFavorites() } }.flow.cachedIn(
            viewModelScope
        )

}