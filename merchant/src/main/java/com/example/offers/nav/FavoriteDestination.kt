@file:OptIn(ExperimentalAnimationApi::class)

package com.example.offers.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.ThriveNavigationDestination
import com.example.offers.ui.FavoriteScreen

object FavoriteDestination : ThriveNavigationDestination {
    override val route = "fav_route"
    override val destination = "fav_destination"
}

fun NavGraphBuilder.favGraph() {
    composable(FavoriteDestination.route) {
        FavoriteScreen()
    }
}