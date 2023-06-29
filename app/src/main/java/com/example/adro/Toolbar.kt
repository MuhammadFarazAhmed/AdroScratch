package com.example.adro

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adro.theme.ThriveScratchTheme
import com.example.adro.ui.ThriveNavigationDestination
import com.example.base.R
import com.example.offers.nav.SearchDestination


@Composable
fun Toolbar(visibility: Boolean, onNavigateToDestination: (ThriveNavigationDestination, String?) -> Unit) {
    AnimatedVisibility(visible = visibility) {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .height(67.dp)
                .fillMaxWidth(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .width(40.dp)
                            .align(Alignment.CenterVertically)
                            .height(40.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hot_ballon),
                            contentScale = ContentScale.Crop,
                            contentDescription = ""
                        )
                    }
                    Text(
                        text = "Faraz Ahmed",
                        color = Color.White,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                }

                Row {
                    Image(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentScale = ContentScale.Inside,
                        contentDescription = "",
                        modifier = Modifier.clickable {
                            onNavigateToDestination(SearchDestination, SearchDestination.route)
                        }

                    )

                    Spacer(modifier = Modifier.padding(4.dp))

                    Image(
                        painter = painterResource(id = R.drawable.ic_notification),
                        contentScale = ContentScale.Inside,
                        contentDescription = "")
                }


            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun ToolbarPreview() {
    ThriveScratchTheme {
        Toolbar(true) { _, _ -> }
    }
}