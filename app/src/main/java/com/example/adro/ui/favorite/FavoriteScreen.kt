package com.example.adro.ui.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.adro.Navigator

@Composable
fun FavoriteScreen(navigator: Navigator) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Button(
            modifier = Modifier.captionBarPadding(),
            onClick = { navigator.navigateTo(Navigator.NavTarget.Favorites) }) {
            Text(text = "Go to detail")
        }
    }
}