package com.example.home.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.adro.AdroNavigationDestination
import com.example.home.ui.HomeScreen

object HomeDestination : AdroNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    navigateToAuth: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = HomeDestination.route,
        startDestination = HomeDestination.destination
    ) {
        composable(HomeDestination.destination) {
            HomeScreen(navigateToAuth = navigateToAuth)
        }
        nestedGraphs()
    }
}
