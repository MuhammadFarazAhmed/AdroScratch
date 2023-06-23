package com.example.home.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.AdroNavigationDestination
import com.example.home.ui.HomeScreen


object HomeDestination : AdroNavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    navigateToAuth: () -> Unit,
    navigateToOffers: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit
//    nestedGraphs: NavGraphBuilder.() -> Unit
) {
//    navigation(
//        route = HomeDestination.route,
//        startDestination = HomeDestination.destination
//    ) {
    composable(HomeDestination.route) {
        HomeScreen(
            navigateToAuth = navigateToAuth,
            navigateToOffers = { navigateToOffers() },
            isApiLoading = { loading: Boolean -> isApiLoading(loading) })
    }
//        nestedGraphs()
//    }
}
