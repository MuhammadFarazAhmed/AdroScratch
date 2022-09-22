package com.example.profile.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.profile.ui.ProfileScreen

object ProfileDestination : AdroNavigationDestination {
    override val route = "profile_route"
    override val destination = "offers_destination"
}

fun NavGraphBuilder.profileGraph() {
    composable(ProfileDestination.route) {
        ProfileScreen()
    }
}
