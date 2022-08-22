package com.example.profile.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.adro.ErrorItem
import com.example.adro.LoadingItem
import com.example.adro.LoadingView
import com.example.adro.common.HexToJetpackColor
import com.example.base.R
import com.example.profile.vm.ProfileViewModel


enum class ProfileSections(val value: String) {
    PROFILE_HEADER("profile_header"),
    MY_ACCOUNT("my_account"),
    REDEMPTIONS_DETAILS("redemption_details"),
    SETTINGS("settings"),
    HELP_SUPPORT("help_support"),
    ABOUT("about"),
    SIGN_OUT("sign_out"),
}

@Composable
fun ProfileScreen() {
    val vm = hiltViewModel<ProfileViewModel>()

    val lazySections = vm.sections.collectAsLazyPagingItems()

    ProfileSectionSwitch()

//    LazyColumn {
//
//        items(lazySections) { section ->
//            when (section?.sectionIdentifier) {
//                ProfileSections.PROFILE_HEADER.value -> ProfileSectionSwitch()
//                ProfileSections.MY_ACCOUNT.value -> {}
//                ProfileSections.REDEMPTIONS_DETAILS.value -> {}
//                ProfileSections.SETTINGS.value -> {}
//                ProfileSections.HELP_SUPPORT.value -> {}
//                ProfileSections.ABOUT.value -> {}
//                ProfileSections.SIGN_OUT.value -> ProfileSectionSignOut()
//            }
//        }
//
//        lazySections.apply {
//            when {
//                loadState.refresh is LoadState.Loading -> {
//                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
//                }
//                loadState.append is LoadState.Loading -> {
//                    item { LoadingItem() }
//                }
//                loadState.refresh is LoadState.Error -> {
//                    val e = lazySections.loadState.refresh as LoadState.Error
//                    item {
//                        ErrorItem(
//                            message = e.error.message,
//                            modifier = Modifier.fillParentMaxSize(),
//                            onClickRetry = { }
//                        )
//                    }
//                }
//                loadState.append is LoadState.Error -> {
//                    val e = lazySections.loadState.append as LoadState.Error
//                    item {
//                        ErrorItem(
//                            message = e.error.message,
//                            onClickRetry = { }
//                        )
//                    }
//                }
//            }
//        }
//
//    }

}


@Composable
fun ProfileHeaderView() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Hello Guest",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.h1,
            color = Color.White
        )
        Text(
            text = "Do you have an Abu Dhabi Golden Visa ?",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.White
        )
        Button(
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = HexToJetpackColor.getColor(
                    "E41C38"
                )
            )
        ) {
            Text(
                text = "SignIn",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }
        Button(
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp, Color.White, RoundedCornerShape(4.dp))
        ) {
            Text(
                text = "Create new Account",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }

    }

}

@Composable
fun ProfileSectionArrow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Profile Detail")
        Icon(
            modifier = Modifier.size(24.dp),
            tint = Color.LightGray,
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24),
            contentDescription = ""
        )
    }
}

@Composable
fun ProfileSectionText() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Profile Detail")
        Text(text = "English")
    }
}

@Preview
@Composable
fun ProfileSectionSwitch() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "Profile Detail")
        CustomSwitch(gapBetweenThumbAndTrackEdge = 0.dp)
    }
}

@Composable
fun ProfileSectionSignOut() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .padding(top = 16.dp),
    ) {
        Text(text = "App version v1.0")
        Button(
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ),
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(4.dp))
        ) {
            Text(
                text = "Sign Out",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.body2,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CustomSwitch(
    scale: Float = 1f,
    width: Dp = 36.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 1.dp,
    checkedFillThumbColor: Color = Color(0xFFFFFFFF),
    uncheckedFillThumbColor: Color = Color(0xFF797474),
    checkedTrackColor: Color = Color(0xFF4CAF50),
    uncheckedTrackColor: Color = Color(0xFFFFFFFF),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp
) {

    val switchON = remember {
        mutableStateOf(true) // Initially the switch is ON
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition = animateFloatAsState(
        targetValue = if (switchON.value)
            with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else
            with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() }
    )

    Canvas(
        modifier = Modifier
            .size(width = width, height = height)
            .scale(scale = scale)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        // This is called when the user taps on the canvas
                        switchON.value = !switchON.value
                    }
                )
            },
    ) {
        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(strokeWidth.toPx())
        )

        // Thumb
        drawCircle(
            color = if (switchON.value) checkedFillThumbColor else uncheckedFillThumbColor,
            radius = thumbRadius.toPx(),
            center = Offset(
                x = animatePosition.value,
                y = size.height / 2
            )
        )
    }
}