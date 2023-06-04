package com.example.domain.models


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("success")
    val success: Boolean? = null
) {
    @Serializable
    data class Data(
        @SerialName("banner_detail")
        val bannerDetail: BannerDetail? = null,
        @SerialName("pending_transaction_deeplink")
        val pendingTransactionDeeplink: String? = null,
        @SerialName("sections")
        val sections: List<Section>
    ) {
        @Serializable
        data class BannerDetail(
            @SerialName("banner_bg_color")
            val bannerBgColor: String? = null,
            @SerialName("banner_text")
            val bannerText: String? = null,
            @SerialName("banner_text_color")
            val bannerTextColor: String? = null,
            @SerialName("should_show_cancel_button")
            val shouldShowCancelButton: Boolean? = null
        )
@Serializable
        data class Section(
            @SerialName("section_identifier")
            val sectionIdentifier: String = "1",
            @SerialName("section_items")
            val sectionItems: List<SectionItem> = arrayListOf(),
            @SerialName("sort_order")
            val sortOrder: Int = 1,
            @SerialName("title")
            val title: String = "title",
            @SerialName("image_url")
            val imageUrl: String = "",
            @SerialName("button_title")
            val buttonTitle: String = "text",
            @SerialName("button_bg_color")
            val buttonBgColor: String = "FF343434",
            @SerialName("sub_title")
            val subTitle: String = ""
        ) {
    @Serializable
            data class SectionItem(
                @SerialName("button_bg_color")
                val buttonBgColor: String = "FF343434",
                @SerialName("button_title")
                val buttonTitle: String = "",
                @SerialName("deeplink")
                val deeplink: String = "",
                @SerialName("id")
                val id: Int = -1,
                @SerialName("image_url")
                val imageUrl: String? = "",
                @SerialName("is_external_link")
                val isExternalLink: String = "",
                @SerialName("should_show_button")
                val shouldShowButton: Int? = -1,
                @SerialName("subtitle")
                val subtitle: String? = "",
                @SerialName("title")
                val title: String? = ""
            )
        }
    }
}