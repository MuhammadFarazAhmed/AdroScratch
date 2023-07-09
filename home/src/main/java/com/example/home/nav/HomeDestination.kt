package com.example.home.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.ThriveNavigationDestination
import com.example.domain.usecase.CommonUseCase
import com.example.home.ui.HomeScreen
import org.koin.androidx.compose.get


object HomeDestination : ThriveNavigationDestination {
    override val route = "home_route"

    override val destination = "home_destination"

    const val homeGraphRoutePattern = "home_graph"

}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.homeGraph(
    navigateToAuth: () -> Unit,
    navigateToOffers: () -> Unit,
    handleDeepLinks: () -> Unit,
    isApiLoading: (loading: Boolean) -> Unit
) {
    composable(HomeDestination.route) {
        HomeScreen(
            navigateToAuth = { navigateToAuth() },
            navigateToOffers = { navigateToOffers() },
            handleDeepLinks = { handleDeepLinks() },
            isApiLoading = { loading: Boolean -> isApiLoading(loading) })
    }
}

