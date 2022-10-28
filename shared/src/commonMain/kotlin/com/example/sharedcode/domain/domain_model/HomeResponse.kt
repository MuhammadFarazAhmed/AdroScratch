package com.example.sharedcode.domain.domain_model

import com.example.sharedcode.CommonParcelable
import com.example.sharedcode.CommonParcelize

@kotlinx.serialization.Serializable
data class HomeResponse(
    val cmd: String? = null,
    val code: Int? = null,
    val `data`: Data? = null,
    val httpResponse: Int? = null,
    val message: String? = null,
    val success: Boolean? = null
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val bannerDetail: BannerDetail? = null,
        val pendingTransactionDeeplink: String? = null,
        val sections: List<Section>
    ) {
        @kotlinx.serialization.Serializable
        data class BannerDetail(
            val bannerBgColor: String? = null,
            val bannerText: String? = null,
            val bannerTextColor: String? = null,
            val shouldShowCancelButton: Boolean? = null
        )
        @kotlinx.serialization.Serializable
        data class Section(
            val sectionIdentifier: String = "1",
            val sectionItems: List<SectionItem> = arrayListOf(),
            val sortOrder: Int = 1,
            val title: String = "title",

            val imageUrl: String = "",

            val buttonTitle: String = "text",

            val buttonBgColor: String = "FF343434",

            val subTitle: String = ""
        ) {
            @kotlinx.serialization.Serializable
            data class SectionItem(

                val buttonBgColor: String = "FF343434",

                val buttonTitle: String = "",

                val deeplink: String = "",

                val id: Int = -1,

                val imageUrl: String? = "",

                val isExternalLink: String? = "",

                val shouldShowButton: Int? = -1,

                val subtitle: String? = "",

                val title: String? = ""
            )
        }
    }
}