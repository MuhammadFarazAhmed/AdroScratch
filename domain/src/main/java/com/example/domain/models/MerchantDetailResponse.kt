package com.example.domain.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MerchantDetailResponse(
    @SerialName("cmd")
    val cmd: String?,
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val `data`: Data?,
    @SerialName("http_response")
    val httpResponse: Int?,
    @SerialName("message")
    val message: String?,
    @SerialName("success")
    val success: Boolean?
) {
    @Serializable
    internal data class Data(
        @SerialName("details")
        val details: List<Detail?>?,
        @SerialName("merchant_id")
        val merchantId: Int?,
        @SerialName("merchant_logo_url")
        val merchantLogoUrl: String?,
        @SerialName("merchant_name")
        val merchantName: String?
    ) {
        @Serializable
        internal data class Detail(
            @SerialName("category_item")
            val categoryItem: List<CategoryItem?>?,
            @SerialName("favourite_merchant")
            val favouriteMerchant: Boolean?,
            @SerialName("image_url")
            val imageUrl: String?,
            @SerialName("info_button_title")
            val infoButtonTitle: String?,
            @SerialName("is_fav")
            val isFav: Boolean?,
            @SerialName("is_show_fav_btn")
            val isShowFavBtn: Boolean?,
            @SerialName("lat")
            val lat: Double?,
            @SerialName("location_description")
            val locationDescription: String?,
            @SerialName("location_name")
            val locationName: String?,
            @SerialName("merchant_name")
            val merchantName: String?,
            @SerialName("offers")
            val offers: List<Offer?>?,
            @SerialName("outlet_description")
            val outletDescription: String?,
            @SerialName("outlets")
            val outlets: List<Outlet?>?,
            @SerialName("section_identifier")
            val sectionIdentifier: String?,
            @SerialName("section_title")
            val sectionTitle: String?
        ) {
            @Serializable
            internal data class CategoryItem(
                @SerialName("item_image")
                val itemImage: String?,
                @SerialName("item_name")
                val itemName: String?
            )

            @Serializable
            internal data class Offer(
                @SerialName("is_delivery_section")
                val isDeliverySection: Boolean?,
                @SerialName("is_monthly_section")
                val isMonthlySection: Boolean?,
                @SerialName("is_show_purchase_button")
                val isShowPurchaseButton: Boolean?,
                @SerialName("offers_to_display")
                val offersToDisplay: List<OffersToDisplay?>?,
                @SerialName("product_id")
                val productId: String?,
                @SerialName("product_sequence")
                val productSequence: Int?,
                @SerialName("product_sku")
                val productSku: String?,
                @SerialName("purchase_product_id")
                val purchaseProductId: Int?,
                @SerialName("section_name")
                val sectionName: String?
            ) {
                @Serializable
                internal data class OffersToDisplay(
                    @SerialName("additional_details")
                    val additionalDetails: List<AdditionalDetail?>?,
                    @SerialName("categories_analytics")
                    val categoriesAnalytics: String?,
                    @SerialName("category")
                    val category: String?,
                    @SerialName("category_color")
                    val categoryColor: String?,
                    @SerialName("dcp_license")
                    val dcpLicense: String?,
                    @SerialName("details")
                    val details: String?,
                    @SerialName("expiration_date")
                    val expirationDate: String?,
                    @SerialName("is_barcode_enabled")
                    val isBarcodeEnabled: Boolean?,
                    @SerialName("is_cheers")
                    val isCheers: Boolean?,
                    @SerialName("is_merlin_offer")
                    val isMerlinOffer: Boolean?,
                    @SerialName("is_pingable")
                    val isPingable: Boolean?,
                    @SerialName("is_pinged")
                    val isPinged: Boolean?,
                    @SerialName("is_point_based_offer")
                    val isPointBasedOffer: Boolean?,
                    @SerialName("is_show_smiles")
                    val isShowSmiles: Boolean?,
                    @SerialName("is_topup_offer_allowed")
                    val isTopupOfferAllowed: Boolean?,
                    @SerialName("item_code")
                    val itemCode: String?,
                    @SerialName("merlin_button_text")
                    val merlinButtonText: String?,
                    @SerialName("merlin_title")
                    val merlinTitle: String?,
                    @SerialName("message")
                    val message: String?,
                    @SerialName("name")
                    val name: String?,
                    @SerialName("offer_detail")
                    val offerDetail: String?,
                    @SerialName("offer_id")
                    val offerId: Int?,
                    @SerialName("offer_redemption_type")
                    val offerRedemptionType: Int?,
                    @SerialName("offer_tag")
                    val offerTag: OfferTag?,
                    @SerialName("outlet_ids")
                    val outletIds: List<Int?>?,
                    @SerialName("outlet_merlin_urls")
                    val outletMerlinUrls: String?,
                    @SerialName("redeemability")
                    val redeemability: Int?,
                    @SerialName("savings_estimate")
                    val savingsEstimate: Int?,
                    @SerialName("savings_estimate_aed")
                    val savingsEstimateAed: Int?,
                    @SerialName("savings_estimate_local_currency")
                    val savingsEstimateLocalCurrency: Int?,
                    @SerialName("smiles_burn_value")
                    val smilesBurnValue: Int?,
                    @SerialName("smiles_earn_value")
                    val smilesEarnValue: Int?,
                    @SerialName("sort_order")
                    val sortOrder: Int?,
                    @SerialName("sub_detail_label")
                    val subDetailLabel: String?,
                    @SerialName("valid_from_date")
                    val validFromDate: String?,
                    @SerialName("validity_date")
                    val validityDate: String?,
                    @SerialName("voucher_details")
                    val voucherDetails: List<VoucherDetail?>?,
                    @SerialName("voucher_restriction1")
                    val voucherRestriction1: Int?,
                    @SerialName("voucher_restriction2")
                    val voucherRestriction2: Int?,
                    @SerialName("voucher_restrictions")
                    val voucherRestrictions: String?,
                    @SerialName("voucher_rules_of_use")
                    val voucherRulesOfUse: List<String?>?,
                    @SerialName("voucher_type")
                    val voucherType: Int?,
                    @SerialName("voucher_type_image")
                    val voucherTypeImage: String?
                ) {
                    @Serializable
                    internal data class AdditionalDetail(
                        @SerialName("color")
                        val color: String?,
                        @SerialName("id")
                        val id: Int?,
                        @SerialName("image")
                        val image: String?,
                        @SerialName("title")
                        val title: String?
                    )

                    @Serializable
                    internal data class OfferTag(
                        @SerialName("tag_bg_color")
                        val tagBgColor: String?,
                        @SerialName("tag_title")
                        val tagTitle: String?,
                        @SerialName("tag_title_color")
                        val tagTitleColor: String?
                    )

                    @Serializable
                    internal data class VoucherDetail(
                        @SerialName("color")
                        val color: String?,
                        @SerialName("id")
                        val id: Int?,
                        @SerialName("image")
                        val image: String?,
                        @SerialName("title")
                        val title: String?
                    )
                }
            }

            @Serializable
            internal data class Outlet(
                @SerialName("delivery_telephone")
                val deliveryTelephone: String?,
                @SerialName("description")
                val description: String?,
                @SerialName("distance")
                val distance: Int?,
                @SerialName("email")
                val email: String?,
                @SerialName("hotel")
                val hotel: String?,
                @SerialName("human_location")
                val humanLocation: String?,
                @SerialName("id")
                val id: Int?,
                @SerialName("is_favourite")
                val isFavourite: Boolean?,
                @SerialName("lat")
                val lat: Double?,
                @SerialName("lng")
                val lng: Double?,
                @SerialName("mall")
                val mall: String?,
                @SerialName("merlin_url")
                val merlinUrl: String?,
                @SerialName("name")
                val name: String?,
                @SerialName("neighborhood")
                val neighborhood: String?,
                @SerialName("telephone")
                val telephone: String?
            )
        }
    }
}