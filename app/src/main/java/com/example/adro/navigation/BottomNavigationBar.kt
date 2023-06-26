package com.example.adro.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy

@Composable
fun BottomNavigationBar(
    bottomBarState: Boolean,
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination, String?) -> Unit,
    currentDestination: NavDestination?
) {
    AnimatedVisibility(visible = bottomBarState) {
        BottomNavigation(contentColor = Color.White) {
            destinations.forEach { destination ->
                val selected =
                    currentDestination?.hierarchy?.any { it.route == destination.route } == true
                BottomNavigationItem(modifier = Modifier
                    .background(Color.White)
                    .navigationBarsPadding(),
                    icon = {
                        val icon = if (selected) {
                            destination.selectedIcon
                        } else {
                            destination.unselectedIcon
                        }
                        when (icon) {
                            is Icon.ImageVectorIcon -> {
                                Image(
                                    imageVector = icon.imageVector,
                                    contentDescription = null
                                )
                            }

                            is Icon.DrawableResourceIcon -> {
                                Image(
                                    painter = painterResource(id = icon.id),
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    label = {
                        Text(
                            stringResource(destination.iconTextId),
                            color = Color.Black
                        )
                    },
                    alwaysShowLabel = true,
                    selected = selected,
                    onClick = { onNavigateToDestination(destination, destination.route) })

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
}
