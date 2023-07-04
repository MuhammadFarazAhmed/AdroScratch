package com.example.profile.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.adro.common.CommonFlowExtensions.collectAsStateLifecycleAware
import com.example.adro.common.CommonUtilsExtension.applyPagination
import com.example.adro.common.HexToJetpackColor
import com.example.adro.components.SwipeToRefreshContainer
import com.example.base.R
import com.example.profile.vm.ProfileViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


enum class ProfileSections(val value: String) {
    PROFILE_HEADER("profile_header"),
    MY_ACCOUNT("my_account"),
    REDEMPTIONS_DETAILS("redemption_details"),
    SETTINGS("settings"),
    HELP_SUPPORT("help_support"),
    ABOUT("about"),
    SIGN_OUT("signout"),
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(navigateToHome: () -> Unit = {}, vm: ProfileViewModel = getViewModel()) {

    val lazySections = vm.sections.collectAsLazyPagingItems()
    val lazySectionsState = rememberLazyListState()
    val isRefreshing by vm.isRefreshing.collectAsStateLifecycleAware()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { lazySections.refresh() })
    val isLogin = vm.isLogin.collectAsStateLifecycleAware()


    SwipeToRefreshContainer(
        pullRefreshState = pullRefreshState,
        isRefreshing = isRefreshing,
        modifier = Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxSize(),
        content = {

            LazyColumn(
                state = lazySectionsState,
                modifier = Modifier.background(HexToJetpackColor.getColor("F1F1F1"))
            ) {

                items(
                    count = lazySections.itemCount,
                    key = lazySections.itemKey(),
                    contentType = lazySections.itemContentType()
                ) { index ->

                    val item = lazySections[index]
                    when (item?.sectionIdentifier) {

                        ProfileSections.PROFILE_HEADER.value -> {
                            if (isLogin.value == false) {
                                ProfileSectionHeader()
                            }
                        }

                        ProfileSections.MY_ACCOUNT.value,
                        ProfileSections.REDEMPTIONS_DETAILS.value,
                        ProfileSections.SETTINGS.value,
                        ProfileSections.HELP_SUPPORT.value,
                        ProfileSections.ABOUT.value,
                        -> {
                            ProfileSectionHeaderRow(item.sectionTitle)
                            item.sectionData.forEach { innerItem ->
                                when (innerItem.type) {
                                    "arrow" -> ProfileSectionArrow(innerItem.title)
                                    "text" -> ProfileSectionText(innerItem.title, innerItem.desc)
                                    "switch" -> ProfileSectionSwitch(innerItem.title, innerItem.value)

                                    else -> ProfileSectionHeaderRow(innerItem.title)
                                }
                            }
                        }

                        ProfileSections.SIGN_OUT.value -> if (isLogin.value == true) {
                            ProfileSectionSignOut(vm)
                        }

                    }
                }

                applyPagination(lazySections)

            }
        }
    )
}


@Composable
fun ProfileSectionHeader() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = HexToJetpackColor.getColor("E41C38"))
        ) {
            Text(
                text = "SignIn",
                modifier = Modifier.padding(vertical = 8.dp),
                style = MaterialTheme.typography.body2,
                color = Color.White
            )
        }
        Button(
            onClick = { },
            modifier = Modifier
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
fun ProfileSectionHeaderRow(sectionTitle: String?) {
    if (sectionTitle != ProfileSections.MY_ACCOUNT.value) Spacer(modifier = Modifier.padding(8.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = sectionTitle ?: "",
            modifier = Modifier.padding(vertical = 8.dp),
            style = MaterialTheme.typography.h1,
            color = Color.Black
        )
    }
}

@Composable
fun ProfileSectionArrow(title: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title ?: "")
        Icon(
            modifier = Modifier.size(24.dp),
            tint = Color.LightGray,
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_baseline_chevron_right_24),
            contentDescription = ""
        )
    }
}

@Composable
fun ProfileSectionText(title: String?, value: String?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title ?: "")
        Text(text = value ?: "")
    }
}

@Composable
fun ProfileSectionSwitch(title: String?, value: Int?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title ?: "")
        CustomSwitch(initialValue = value == 1, gapBetweenThumbAndTrackEdge = 0.dp) { _ ->

        }
    }
}

@Composable
fun ProfileSectionSignOut(vm: ProfileViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 8.dp)
            .padding(16.dp),
    ) {
        Text(text = "App version v1.0")
        Button(
            elevation = ButtonDefaults.elevation(0.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            onClick = {
                vm.signOut()
            },
            modifier = Modifier
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
    width: Dp = 38.dp,
    height: Dp = 20.dp,
    strokeWidth: Dp = 1.dp,
    initialValue: Boolean = false,
    checkedFillThumbColor: Color = Color(0xFFFFFFFF),
    checkedTrackColor: Color = Color(0xFFACCCBC),
    uncheckedTrackColor: Color = Color(0xBABABABA),
    gapBetweenThumbAndTrackEdge: Dp = 4.dp,
    callback: (checked: Boolean) -> Unit = {}
) {
    val switchON = remember {
        mutableStateOf(initialValue) // Initially the switch is ON
    }

    val thumbRadius = (height / 2) - gapBetweenThumbAndTrackEdge

    // To move thumb, we need to calculate the position (along x axis)
    val animatePosition =
        animateFloatAsState(targetValue = if (switchON.value) with(LocalDensity.current) { (width - thumbRadius - gapBetweenThumbAndTrackEdge).toPx() }
        else with(LocalDensity.current) { (thumbRadius + gapBetweenThumbAndTrackEdge).toPx() })

    Canvas(modifier = Modifier
        .size(width = width, height = height)
        .scale(scale = scale)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                // This is called when the user taps on the canvas
                switchON.value = !switchON.value
                callback(switchON.value)
            })
        }) {
        // Track
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else uncheckedTrackColor,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = strokeWidth.toPx())
        )

        // Track Fill Area
        drawRoundRect(
            color = if (switchON.value) checkedTrackColor else Color.White,
            cornerRadius = CornerRadius(x = 10.dp.toPx(), y = 10.dp.toPx()),
        )

        // Thumb fill area
        drawCircle(
            color = if (switchON.value) checkedFillThumbColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(x = animatePosition.value, y = size.height / 2)
        )

        // Thumb track
        drawCircle(
            color = if (switchON.value) uncheckedTrackColor else uncheckedTrackColor,
            radius = thumbRadius.toPx(),
            center = Offset(x = animatePosition.value, y = size.height / 2),
            style = Stroke(width = strokeWidth.toPx())
        )
    }
}
