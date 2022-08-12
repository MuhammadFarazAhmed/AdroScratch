package com.example.home.ui.models

data class Data(
    val banner_detail: BannerDetail,
    val pending_transaction_deeplink: String,
    val sections: List<Section>
)