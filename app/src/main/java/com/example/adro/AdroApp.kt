package com.example.adro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.adro.navigation.BottomNavigationBar
import com.example.adro.navigation.AdroNavHost
import com.example.adro.theme.AdroScratchTheme

@Composable
fun AdroApp(appState: AdroAppState = rememberAdroAppState()) {

    AdroScratchTheme {
        Scaffold(
            topBar = {
                Toolbar(appState.shouldShowNavRail)
            },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    AdroNavHost(
                        navController = appState.navController,
                        onBackClick = appState::onBackClick,
                        onNavigateToDestination = appState::navigate,
                        shouldShowNavRail = appState.shouldShowNavRail,
                        shouldShowBottomBar = appState.shouldShowBottomBar)
            }
    }, bottomBar = {
        BottomNavigationBar(
            visibility = appState.shouldShowBottomBar,
            destinations = appState.topLevelDestinations,
            onNavigateToDestination = appState::navigate,
            currentDestination = appState.currentDestination
        )
    })
}
}
