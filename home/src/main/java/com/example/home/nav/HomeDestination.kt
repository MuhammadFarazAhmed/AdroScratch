package com.example.home.nav

import android.content.Intent
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.adro.AdroNavigationDestination
import com.example.home.ui.HomeScreen

object HomeDestination : AdroNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    navigateToAuth: () -> Unit,
    navigateToOffers: (deepLink: String) -> Unit
//    nestedGraphs: NavGraphBuilder.() -> Unit
) {
//    navigation(
//        route = HomeDestination.route,
//        startDestination = HomeDestination.destination
//    ) {
    composable(HomeDestination.route) {
        HomeScreen(navigateToAuth = navigateToAuth, navigateToOffers = { navigateToOffers(it) })
    }
//        nestedGraphs()
//    }
}
