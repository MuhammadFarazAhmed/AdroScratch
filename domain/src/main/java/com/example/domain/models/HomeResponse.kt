package com.example.domain.models


import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("cmd")
    val cmd: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("http_response")
    val httpResponse: Int? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("success")
    val success: Boolean? = null
) {
    data class Data(
        @SerializedName("banner_detail")
        val bannerDetail: BannerDetail? = null,
        @SerializedName("pending_transaction_deeplink")
        val pendingTransactionDeeplink: String? = null,
        @SerializedName("sections")
        val sections: List<Section>
    ) {
        data class BannerDetail(
            @SerializedName("banner_bg_color")
            val bannerBgColor: String? = null,
            @SerializedName("banner_text")
            val bannerText: String? = null,
            @SerializedName("banner_text_color")
            val bannerTextColor: String? = null,
            @SerializedName("should_show_cancel_button")
            val shouldShowCancelButton: Boolean? = null
        )

        data class Section(
            @SerializedName("section_identifier")
            val sectionIdentifier: String = "1",
            @SerializedName("section_items")
            val sectionItems: List<SectionItem> = arrayListOf(),
            @SerializedName("sort_order")
            val sortOrder: Int = 1,
            @SerializedName("title")
            val title: String = "title",
            @SerializedName("image_url")
            val imageUrl: String = "",
            @SerializedName("button_title")
            val buttonTitle: String = "text",
            @SerializedName("button_bg_color")
            val buttonBgColor: String = "FF343434",
            @SerializedName("sub_title")
            val subTitle: String = ""
        ) {
            data class SectionItem(
                @SerializedName("button_bg_color")
                val buttonBgColor: String = "FF343434",
                @SerializedName("button_title")
                val buttonTitle: String = "",
                @SerializedName("deeplink")
                val deeplink: String = "",
                @SerializedName("id")
                val id: Int = -1,
                @SerializedName("image_url")
                val imageUrl: String? = "",
                @SerializedName("is_external_link")
                val isExternalLink: Any? = "",
                @SerializedName("should_show_button")
                val shouldShowButton: Int? = -1,
                @SerializedName("subtitle")
                val subtitle: String? = "",
                @SerializedName("title")
                val title: String? = ""
            )
        }
    }
}