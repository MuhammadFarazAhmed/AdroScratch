package com.example.domain.models

import com.example.home.ui.models.BannerDetail

data class Data(
    val banner_detail: BannerDetail,
    val pending_transaction_deeplink: String,
    val sections: List<Section>
)