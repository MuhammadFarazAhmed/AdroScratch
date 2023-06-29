package com.example.profile.nav

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.example.adro.ui.ThriveNavigationDestination
import com.example.profile.ui.ProfileScreen

object ProfileDestination : ThriveNavigationDestination {
    override val route = "profile_route"
    override val destination = "offers_destination"
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.profileGraph(navigateToHome: () -> Unit) {
    composable(ProfileDestination.route) {
        ProfileScreen(navigateToHome)
    }
}
