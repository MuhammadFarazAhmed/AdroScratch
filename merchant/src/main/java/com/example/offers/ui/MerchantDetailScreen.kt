package com.example.offers.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.adro.common.CommonFlowExtensions.findActivity
import com.example.offers.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun MerchantDetailScreen() {

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        // Update all of the system bar colors to be transparent, and use
        // dark icons if we're in light theme
        systemUiController.setSystemBarsColor(
            color = Color.Transparent,
            darkIcons = true
        )


        // setStatusBarColor() and setNavigationBarColor() also exist

        onDispose {
            systemUiController.setStatusBarColor(
                color = Color.Black,
                darkIcons = false
            )
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {

        Box {
            Header(modifier = Modifier)
        }

    }
}

@Composable
private fun Header(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color.Blue)
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {

        AsyncImage(
            modifier = modifier.height(200.dp),
            model = com.example.base.R.drawable.demoimage,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                modifier = modifier.size(85.dp),
                model = com.example.base.R.drawable.ic_back,
                contentDescription = ""
            )
            AsyncImage(
                modifier = modifier
                    .size(85.dp),
                model = com.example.base.R.drawable.ic_favorite,
                contentDescription = ""
            )
        }

    }
}
