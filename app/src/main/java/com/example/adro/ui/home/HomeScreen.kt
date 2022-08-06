package com.example.adro.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.adro.vm.HomeViewModel


@Composable
fun HomeScreen() {
    val vm = hiltViewModel<HomeViewModel>()
    var buttonText by remember { mutableStateOf(vm.buttonText) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Button(
            modifier = Modifier.captionBarPadding(),
            onClick = { buttonText = "Android" }) {
            Text(text = buttonText)
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Button(
            modifier = Modifier.captionBarPadding(),
            onClick = { }) {
            Text(text = "Go to detail")
        }
    }
}