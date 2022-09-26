package com.example.adro.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomNavigationBar(
    visibility: MutableState<Boolean>,
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?
) {
    AnimatedVisibility(
        visible = visibility.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            BottomNavigation(
                contentColor = Color.White
            ) {
                destinations.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    BottomNavigationItem(
                        modifier = Modifier.background(Color.White),
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
                        onClick = { onNavigateToDestination(destination) }
                    )
                }
            }
        })

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
}
