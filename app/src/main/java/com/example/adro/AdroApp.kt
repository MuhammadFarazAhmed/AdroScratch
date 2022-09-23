package com.example.adro

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.example.adro.navigation.BottomNavigationBar
import com.example.adro.navigation.AdroNavHost
import com.example.adro.theme.AdroScratchTheme

@Composable
fun AdroApp(appState: AdroAppState = rememberAdroAppState()) {

    AdroScratchTheme {

        val topBarState = rememberSaveable { (mutableStateOf(true)) }

        Scaffold(topBar = { Toolbar(topBarState) },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    AdroNavHost(
                        navController = appState.navController,
                        onBackClick = appState::onBackClick,
                        onNavigateToDestination = appState::navigate,
                        topAppBar = topBarState
                    )
                }
            }, bottomBar = {
                BottomNavigationBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigate,
                    currentDestination = appState.currentDestination
                )
            })

    }

}
