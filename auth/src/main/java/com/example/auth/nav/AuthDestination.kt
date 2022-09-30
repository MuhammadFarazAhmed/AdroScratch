package com.example.auth.nav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.adro.AdroNavigationDestination
import com.example.auth.ui.AuthScreen

object AuthDestination : AdroNavigationDestination {
    override val route = "auth_route"
    override val destination = "auth_destination"
}

fun NavGraphBuilder.authGraph(onBackClick: () -> Unit) {
    
    composable(AuthDestination.route) {
        AuthScreen(onBackClick = onBackClick)
    }
    
}
