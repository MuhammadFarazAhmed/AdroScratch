package com.example.auth.nav

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavGraphBuilder
import com.example.adro.navigation.ThriveNavigationDestination
import com.example.auth.ui.AuthScreen
import com.google.accompanist.navigation.animation.composable

object AuthDestination : ThriveNavigationDestination {
    override val route = "auth_route"
    override val destination = "auth_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.authGraph(onBackClick: () -> Unit, popBack: () -> Unit) {

    composable(
        AuthDestination.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        }) {
        AuthScreen(onBackClick = onBackClick, onLoginSuccess = popBack)
    }

}
