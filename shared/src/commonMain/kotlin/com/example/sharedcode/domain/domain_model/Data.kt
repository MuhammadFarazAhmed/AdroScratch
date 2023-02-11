package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("pending_transaction_deeplink")
    var pendingTransactionDeeplink: String? = null,
    @SerialName("banner_detail")
    var bannerDetail: BannerDetail? = null,
    @SerialName("sections")
    var sections: List<Section>? = null
)