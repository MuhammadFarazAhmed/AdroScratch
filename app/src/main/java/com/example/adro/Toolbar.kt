package com.example.adro

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.adro.theme.AdroScratchTheme


@Composable fun Toolbar(visibility: MutableState<Boolean>) {
    AnimatedVisibility(
        visible = visibility.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .height(67.dp)
                    .fillMaxWidth(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Card(
                            shape = CircleShape,
                            modifier = Modifier.width(40.dp).align(Alignment.CenterVertically)
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
                            contentDescription = ""
                        )

                        Spacer(modifier = Modifier.padding(4.dp))

                        Image(
                            painter = painterResource(id = R.drawable.ic_notification),
                            contentScale = ContentScale.Inside,
                            contentDescription = ""
                        )
                    }


                }
            }
        })
}

@Preview(uiMode = UI_MODE_NIGHT_NO) @Composable fun ToolbarPreview() {
    AdroScratchTheme {
        Toolbar(mutableStateOf(true))
    }
}