package com.example.profile.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.captionBarPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.profile.vm.ProfileViewModel

@Composable
@Preview
fun ProfileScreen() {
    val vm = hiltViewModel<ProfileViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Button(
            modifier = Modifier.captionBarPadding(),
            onClick = { vm.fetchHomeData() }) {
            Text(text = "Go to detail")
        }
    }
}