package com.example.adro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Toolbar(text: String, isBackIconShown: Boolean = true, onBackArrowClicked: () -> Unit) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
    ) {

        takeIf { isBackIconShown }?.let {
            Icon(
                Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier
                    .size(55.dp)
                    .padding(12.dp)
                    .clickable {
                        onBackArrowClicked()
                    }
            )
        }

        Text(
            modifier = Modifier.padding(12.dp),
            text = text,
            color = Color.White,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h1
        )
    }
}
