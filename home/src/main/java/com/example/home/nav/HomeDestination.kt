package com.example.home.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.adro.navigation.ThriveNavigationDestination
import com.google.accompanist.navigation.animation.composable
import com.example.home.nav.HomeDestination.homeGraphRoutePattern
import com.example.home.ui.HomeScreen
import com.google.accompanist.navigation.animation.navigation


object HomeDestination : ThriveNavigationDestination {
    override val route = "home_route"

    override val destination = "home_destination"

    const val homeGraphRoutePattern = "home_graph"

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    navigateToAuth: () -> Unit,
    navigateToOffers: () -> Unit,
    handleDeepLinks: (deepLink: String) -> Unit,
    isApiLoading: (loading: Boolean) -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit,
) {
    navigation(
        route = homeGraphRoutePattern,
        startDestination = HomeDestination.route
    ) {
        composable(HomeDestination.route) {
            HomeScreen(
                navigateToAuth = { navigateToAuth() },
                navigateToOffers = { navigateToOffers() },
                handleDeepLinks = { deepLink -> handleDeepLinks(deepLink) },
                isApiLoading = { loading: Boolean -> isApiLoading(loading) })
        }
        nestedGraphs()
    }
}

