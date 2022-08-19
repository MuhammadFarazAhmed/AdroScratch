package com.example.profile.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.adro.ErrorItem
import com.example.adro.LoadingItem
import com.example.adro.LoadingView
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
@Preview
fun ProfileScreen() {
    val vm = hiltViewModel<ProfileViewModel>()

    val lazySections = vm.sections.collectAsLazyPagingItems()

    LazyColumn {

        items(lazySections) { section ->
            when (section?.sectionIdentifier) {
                ProfileSections.PROFILE_HEADER.value -> {}
                ProfileSections.MY_ACCOUNT.value -> {}
                ProfileSections.REDEMPTIONS_DETAILS.value -> {}
                ProfileSections.SETTINGS.value -> {}
                ProfileSections.HELP_SUPPORT.value -> {}
                ProfileSections.ABOUT.value -> {}
                ProfileSections.SIGN_OUT.value -> {}
            }
        }

        lazySections.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { LoadingView(modifier = Modifier.fillParentMaxSize()) }
                }
                loadState.append is LoadState.Loading -> {
                    item { LoadingItem() }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazySections.loadState.refresh as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.message,
                            modifier = Modifier.fillParentMaxSize(),
                            onClickRetry = {  }
                        )
                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazySections.loadState.append as LoadState.Error
                    item {
                        ErrorItem(
                            message = e.error.message,
                            onClickRetry = {  }
                        )
                    }
                }
            }
        }

    }

}