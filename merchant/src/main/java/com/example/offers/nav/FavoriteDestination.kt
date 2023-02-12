package com.example.offers.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.offers.ui.OffersScreen

object FavoriteDestination : AdroNavigationDestination {
    override val route = "fav_route"
    override val destination = "fav_destination"
}

fun NavGraphBuilder.favGraph() {
    composable(FavoriteDestination.route) {
        //FavoriteScreen()
    }
}