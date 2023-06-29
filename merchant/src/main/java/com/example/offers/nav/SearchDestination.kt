@file:OptIn(ExperimentalAnimationApi::class)

package com.example.offers.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.example.adro.ui.ThriveNavigationDestination
import com.example.offers.ui.SearchScreen
import com.google.accompanist.navigation.animation.composable

object SearchDestination : ThriveNavigationDestination {
    override val route = "search_route"
    override val destination = "search_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.searchGraph(navigateToDetail: () -> Unit) {
    composable(SearchDestination.route) {
        SearchScreen(navigateToDetail = { navigateToDetail() })
    }
}