package com.example.domain.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class OffersResponse(
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data,
    @SerialName("http_response")
    val httpResponse: Int? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("success")
    val success: Boolean? = null
) :Parcelable {
    @Serializable
    @Parcelize
    data class Data(
        @SerialName("favourite_merchant")
        val favouriteMerchant: Int? = null,
        @SerialName("featured_merchants")
        val featuredMerchants: List<String?>? = null,
        @SerialName("is_elastic_search_results")
        val isElasticSearchResults: Boolean? = null,
        @SerialName("is_elastic_search_server_down")
        val isElasticSearchServerDown: Boolean? = null,
        @SerialName("map_zoom_level")
        val mapZoomLevel: Double? = null,
        @SerialName("offset")
        val offset: Int? = null,
        @SerialName("outlets")
        val outlets: List<Outlet> = arrayListOf(),
        @SerialName("page_size")
        val pageSize: Int? = null,
        @SerialName("ping_section")
        val pingSection: PingSection? = null,
        @SerialName("print_query")
        val printQuery: String? = null,
        @SerialName("records_in_current_page")
        val recordsInCurrentPage: Int? = null,
        @SerialName("search_results")
        val searchResults: List<String?>? = null,
        @SerialName("show_delivery_purchase_view")
        val showDeliveryPurchaseView: Boolean? = null,
        @SerialName("total_records")
        val totalRecords: Int? = null
    ): Parcelable {
        @Serializable
        @Parcelize
        data class Outlet(
            @SerialName("attributes")
            val attributes: List<Attribute?>? = null,
            @SerialName("concept_id")
            val conceptId: String? = null,
            @SerialName("distance")
            val distance: Int? = null,
            @SerialName("email")
            val email: String? = null,
            @SerialName("fuzzy_relevance")
            val fuzzyRelevance: Int? = null,
            @SerialName("human_location")
            val humanLocation: String? = null,
            @SerialName("id")
            val id: Int? = null,
            @SerialName("is_favourite")
            val isFavourite: Boolean? = null,
            @SerialName("is_purchased")
            val isPurchased: Boolean? = null,
            @SerialName("is_redeemable")
            val isRedeemable: Boolean? = null,
            @SerialName("lat")
            val lat: Double? = null,
            @SerialName("lng")
            val lng: Double? = null,
            @SerialName("locked_image_url")
            val lockedImageUrl: String? = null,
            @SerialName("merchant")
            val merchant: Merchant? = null,
            @SerialName("merchant_logo_small_url")
            val merchantLogoSmallUrl: String? = null,
            @SerialName("merchant_logo_url")
            val merchantLogoUrl: String? = null,
            @SerialName("merchant_name")
            val merchantName: String? = null,
            @SerialName("merchant_photo_small_url")
            val merchantPhotoSmallUrl: String? = null,
            @SerialName("merchant_photo_url")
            val merchantPhotoUrl: String? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("product_id")
            val productId: List<Int?>? = null,
            @SerialName("product_sku")
            val productSku: List<String?>? = null,
            @SerialName("sfId")
            val sfId: String? = null,
            @SerialName("tag_type_points")
            val tagTypePoints: Boolean? = null,
            @SerialName("tag_type_rewards")
            val tagTypeRewards: Boolean? = null,
            @SerialName("tag_type_show_n_go")
            val tagTypeShowNGo: Boolean? = null,
            @SerialName("tags")
            val tags: List<Tag?>? = null,
            @SerialName("top_offer_redeemability")
            val topOfferRedeemability: Int? = null
        ):Parcelable {
            @Serializable
            @Parcelize
            data class Attribute(
                @SerialName("type")
                val type: String? = null,
                @SerialName("value")
                val value: String? = null
            ):Parcelable
            @Serializable
            @Parcelize
            data class Merchant(
                @SerialName("id")
                val id: Int? = null,
                @SerialName("name")
                val name: String? = null,
                @SerialName("name_for_outlet")
                val nameForOutlet: String? = null
            ):Parcelable

            @Serializable
            @Parcelize
            data class Tag(
                @SerialName("abbreviated_text")
                val abbreviatedText: String? = null,
                @SerialName("bg_color")
                val bgColor: String? = null,
                @SerialName("icon")
                val icon: String? = null,
                @SerialName("identifier")
                val identifier: String? = null,
                @SerialName("order_id")
                val orderId: Int? = null,
                @SerialName("title")
                val title: String? = null,
                @SerialName("title_color")
                val titleColor: String? = null,
                @SerialName("uid")
                val uid: String? = null
            ):Parcelable
        }
        @Serializable
        @Parcelize
        class PingSection : Parcelable
    }
}

fun OffersResponse.asList() = data.outlets