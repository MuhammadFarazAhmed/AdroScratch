package com.example.offers.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MerchantDetailScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {
        Text(text = "Merchant Detail")
    }

}