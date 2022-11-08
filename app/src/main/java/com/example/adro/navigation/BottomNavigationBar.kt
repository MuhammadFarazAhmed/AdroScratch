package com.example.adro.navigation

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
    bottomBarState: MutableState<Boolean>,
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination, String?, String?) -> Unit,
    currentDestination: NavDestination?
) {

    AnimatedVisibility(visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation(contentColor = Color.White) {
                destinations.forEach { destination ->
                    val selected =
                        currentDestination?.hierarchy?.any { it.route == destination.route } == true
                    BottomNavigationItem(modifier = Modifier.background(Color.White),
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
                        onClick = { onNavigateToDestination(destination, null, null) })
                }
            }
        })

}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
}
