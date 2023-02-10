package com.example.sharedcode.domain.domain_model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("pending_transaction_deeplink")
    val pendingTransactionDeeplink: String? = null,
    @SerialName("banner_detail")
    val bannerDetail: BannerDetail? = null,
    @SerialName("sections")
    val sections: List<Section>? = null
)