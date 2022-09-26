package com.example.auth.nav

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.auth.ui.AuthScreen

object AuthDestination : AdroNavigationDestination {
    override val route = "auth_route"
    override val destination = "auth_destination"
}

fun NavGraphBuilder.authGraph(
    onBackClick: () -> Unit,
    shouldShowNavRail: MutableState<Boolean>,
    shouldShowBottomBar: MutableState<Boolean>
) {

    composable(AuthDestination.route) {
        AuthScreen(
            onBackClick = onBackClick, shouldShowNavRail,
            shouldShowBottomBar,
        )
    }

}

