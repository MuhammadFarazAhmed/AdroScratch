package com.example.adro.ui.offers

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun OffersScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "textProvider()")
    }
}

@Preview
@Composable
fun OffersScreenPreview() {
    OffersScreen()
}
