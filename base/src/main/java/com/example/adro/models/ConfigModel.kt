package com.example.adro.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigModel(
    @SerialName("success")
    val success: Boolean? = null,
    @SerialName("message")
    val message: String? = null,
    @SerialName("code")
    val code: Int? = null,
    @SerialName("data")
    val `data`: Data? = null,
    @SerialName("cmd")
    val cmd: String? = null,
    @SerialName("http_response")
    val httpResponse: Int? = null
) {
    @Serializable
    data class Data(
        @SerialName("configs")
        val configs: Configs? = null,
        @SerialName("location")
        val location: Location? = null,
        @SerialName("instructions_list")
        val instructionsList: List<String?>? = null,
        @SerialName("visa_category_list")
        val visaCategoryList: List<VisaCategory?>? = null,
        @SerialName("tutorials_list")
        val tutorialsList: List<String?>? = null,
        @SerialName("interested_categories")
        val interestedCategories: List<String?>? = null,
        @SerialName("password_rules")
        val passwordRules: List<PasswordRule?>? = null
    ) {
        @Serializable
        data class Configs(
            @SerialName("license_agreement")
            val licenseAgreement: String? = null,
            @SerialName("privacy_policy")
            val privacyPolicy: String? = null,
            @SerialName("help_and_live_chat")
            val helpAndLiveChat: String? = null,
            @SerialName("faqs")
            val faqs: String? = null,
            @SerialName("demographics_form_cancelable")
            val demographicsFormCancelable: String? = null,
            @SerialName("demographics_is_active")
            val demographicsIsActive: String? = null,
            @SerialName("is_captcha_verification")
            val isCaptchaVerification: Boolean? = null,
            @SerialName("is_s_p")
            val isSP: String? = null,
            @SerialName("countries_version")
            val countriesVersion: String? = null,
            @SerialName("filters_version")
            val filtersVersion: String? = null,
            @SerialName("terms_and_conditions")
            val termsAndConditions: String? = null,
            @SerialName("instruction_screen_text_1")
            val instructionScreenText1: String? = null,
            @SerialName("instruction_screen_url_1")
            val instructionScreenUrl1: String? = null,
            @SerialName("instruction_screen_text_2")
            val instructionScreenText2: String? = null,
            @SerialName("instruction_screen_url_2")
            val instructionScreenUrl2: String? = null,
            @SerialName("instruction_screen_text_3")
            val instructionScreenText3: String? = null,
            @SerialName("instruction_screen_url_3")
            val instructionScreenUrl3: String? = null,
            @SerialName("instruction_screen_text_4")
            val instructionScreenText4: String? = null,
            @SerialName("instruction_screen_url_4")
            val instructionScreenUrl4: String? = null,
            @SerialName("instruction_screen_text_5")
            val instructionScreenText5: String? = null,
            @SerialName("instruction_screen_url_5")
            val instructionScreenUrl5: String? = null,
            @SerialName("email_service_enabled")
            val emailServiceEnabled: Boolean? = null,
            @SerialName("contact_email")
            val contactEmail: String? = null,
            @SerialName("gdpr_consent_title")
            val gdprConsentTitle: String? = null,
            @SerialName("gdpr_consent_message")
            val gdprConsentMessage: String? = null,
            @SerialName("gdpr_consent_yes")
            val gdprConsentYes: String? = null,
            @SerialName("gdpr_consent_no")
            val gdprConsentNo: String? = null,
            @SerialName("enable_signature_encryption")
            val enableSignatureEncryption: Boolean? = null,
            @SerialName("tutorials_max_allowed_sessions")
            val tutorialsMaxAllowedSessions: String? = null,
            @SerialName("yas_benefits_group_for_residents")
            val yasBenefitsGroupForResidents: String? = null,
            @SerialName("yas_benefits_group_for_employees")
            val yasBenefitsGroupForEmployees: String? = null,
            @SerialName("points_gifting_denominations")
            val pointsGiftingDenominations: String? = null,
            @SerialName("terms_and_conditions_ar")
            val termsAndConditionsAr: String? = null,
            @SerialName("point_gifting_notification_title")
            val pointGiftingNotificationTitle: String? = null,
            @SerialName("point_gifting_notification_desc")
            val pointGiftingNotificationDesc: String? = null,
            @SerialName("interested_category_1_ar")
            val interestedCategory1Ar: String? = null,
            @SerialName("interested_category_2_ar")
            val interestedCategory2Ar: String? = null,
            @SerialName("interested_category_3_ar")
            val interestedCategory3Ar: String? = null,
            @SerialName("display_yas_benefits_list")
            val displayYasBenefitsList: Boolean? = null,
            @SerialName("interested_category_4_ar")
            val interestedCategory4Ar: String? = null,
            @SerialName("interested_category_5_ar")
            val interestedCategory5Ar: String? = null,
            @SerialName("interested_category_6_ar")
            val interestedCategory6Ar: String? = null,
            @SerialName("interested_category_7_ar")
            val interestedCategory7Ar: String? = null,
            @SerialName("interested_category_8_ar")
            val interestedCategory8Ar: String? = null,
            @SerialName("charity_donation_denominations")
            val charityDonationDenominations: String? = null,
            @SerialName("charity_donation_deeplink")
            val charityDonationDeeplink: String? = null,
            @SerialName("favourite_outlets_params")
            val favouriteOutletsParams: String? = null,
            @SerialName("show_and_go_tab_id")
            val showAndGoTabId: String? = null,
            @SerialName("currency")
            val currency: String? = null,
            @SerialName("support_phone")
            val supportPhone: Boolean? = null,
            @SerialName("hotel_rule_of_use")
            val hotelRuleOfUse: String? = null,
            @SerialName("rule_of_use")
            val ruleOfUse: String? = null,
            @SerialName("error_code_message_en")
            val errorCodeMessageEn: String? = null,
            @SerialName("error_code_message_ar")
            val errorCodeMessageAr: String? = null,
            @SerialName("emid_placeholder_text")
            val emidPlaceholderText: String? = null,
            @SerialName("is_bell_icon_enabled")
            val isBellIconEnabled: Boolean? = null,
            @SerialName("is_visa_category_popup_enabled")
            val isVisaCategoryPopupEnabled: Boolean? = null,
            @SerialName("emid_starting_text")
            val emidStartingText: String? = null,
            @SerialName("invoice_validation_success_message_upgraded")
            val invoiceValidationSuccessMessageUpgraded: String? = null,
            @SerialName("invoice_validation_outdated_invoice")
            val invoiceValidationOutdatedInvoice: String? = null,
            @SerialName("gems_points_reflect_message")
            val gemsPointsReflectMessage: String? = null,
            @SerialName("Two_People_")
            val twoPeople: String? = null,
            @SerialName("savings_message_welcome_guest")
            val savingsMessageWelcomeGuest: String? = null,
            @SerialName("tab_name_all_offers_crg")
            val tabNameAllOffersCrg: String? = null,
            @SerialName("tab_name_all_offers_jge")
            val tabNameAllOffersJge: String? = null,
            @SerialName("tab_name_all_offers_landmark_group")
            val tabNameAllOffersLandmarkGroup: String? = null,
            @SerialName("tab_name_BNE")
            val tabNameBNE: String? = null,
            @SerialName("tab_name_HSBC")
            val tabNameHSBC: String? = null,
            @SerialName("tab_name_all_offers")
            val tabNameAllOffers: String? = null,
            @SerialName("tab_name_new")
            val tabNameNew: String? = null,
            @SerialName("tab_name_monthly")
            val tabNameMonthly: String? = null,
            @SerialName("tab_name_delivery")
            val tabNameDelivery: String? = null,
            @SerialName("tab_name_cheers")
            val tabNameCheers: String? = null,
            @SerialName("tab_name_more_sa")
            val tabNameMoreSa: String? = null,
            @SerialName("tab_name_buy_more")
            val tabNameBuyMore: String? = null,
            @SerialName("tab_name_hsbc_fine_dining")
            val tabNameHsbcFineDining: String? = null,
            @SerialName("tab_name_hsbc_i_love_dining")
            val tabNameHsbcILoveDining: String? = null,
            @SerialName("tab_name_all_offers_gems")
            val tabNameAllOffersGems: String? = null,
            @SerialName("tab_name_all_offers_kelloggs")
            val tabNameAllOffersKelloggs: String? = null,
            @SerialName("gems_featured_heading")
            val gemsFeaturedHeading: String? = null,
            @SerialName("gems_more_to_enjoy")
            val gemsMoreToEnjoy: String? = null,
            @SerialName("upgrade_app")
            val upgradeApp: String? = null,
            @SerialName("upgrade_app_lmk")
            val upgradeAppLmk: String? = null,
            @SerialName("buy_more")
            val buyMore: String? = null,
            @SerialName("hsbc_more_to_enjoy")
            val hsbcMoreToEnjoy: String? = null,
            @SerialName("hsbc_more_to_enjoy_group2")
            val hsbcMoreToEnjoyGroup2: String? = null,
            @SerialName("qgi_more_to_enjoy")
            val qgiMoreToEnjoy: String? = null,
            @SerialName("vdf_more_to_enjoy")
            val vdfMoreToEnjoy: String? = null,
            @SerialName("more_from_vodafone")
            val moreFromVodafone: String? = null,
            @SerialName("gems_user_id_and_password_is_required")
            val gemsUserIdAndPasswordIsRequired: String? = null,
            @SerialName("user_already_exisit_gems_message")
            val userAlreadyExisitGemsMessage: String? = null,
            @SerialName("user_already_registered_gems_message")
            val userAlreadyRegisteredGemsMessage: String? = null,
            @SerialName("user_already_registered_yah_message")
            val userAlreadyRegisteredYahMessage: String? = null,
            @SerialName("parent_id_is_required")
            val parentIdIsRequired: String? = null,
            @SerialName("parent_id_is_required_dpr")
            val parentIdIsRequiredDpr: String? = null,
            @SerialName("parent_id_is_required_dhl")
            val parentIdIsRequiredDhl: String? = null,
            @SerialName("parent_id_is_required_uex")
            val parentIdIsRequiredUex: String? = null,
            @SerialName("emp_code_is_required")
            val empCodeIsRequired: String? = null,
            @SerialName("SAVED_THIS_YEAR_LABEL")
            val sAVEDTHISYEARLABEL: String? = null,
            @SerialName("OUTLET_COUNT_SINGULAR")
            val oUTLETCOUNTSINGULAR: String? = null,
            @SerialName("OUTLET_COUNT_PLURAL")
            val oUTLETCOUNTPLURAL: String? = null,
            @SerialName("TRAVEL_LOCATION")
            val tRAVELLOCATION: String? = null,
            @SerialName("email_required")
            val emailRequired: String? = null,
            @SerialName("email_password_required")
            val emailPasswordRequired: String? = null,
            @SerialName("invalid_vip_key")
            val invalidVipKey: String? = null,
            @SerialName("email_not_exist")
            val emailNotExist: String? = null,
            @SerialName("linked_entertainer_account_to_facebook")
            val linkedEntertainerAccountToFacebook: String? = null,
            @SerialName("wrong_device_install_token")
            val wrongDeviceInstallToken: String? = null,
            @SerialName("wrong_session_token")
            val wrongSessionToken: String? = null,
            @SerialName("problems_with_saving_of_family_member_info")
            val problemsWithSavingOfFamilyMemberInfo: String? = null,
            @SerialName("vip_key_already_used")
            val vipKeyAlreadyUsed: String? = null,
            @SerialName("success_vip_key_applied_and_product_unlocked")
            val successVipKeyAppliedAndProductUnlocked: String? = null,
            @SerialName("invalid_email_address")
            val invalidEmailAddress: String? = null,
            @SerialName("not_implemented")
            val notImplemented: String? = null,
            @SerialName("social_id_not_tied_to_magento_customer_account")
            val socialIdNotTiedToMagentoCustomerAccount: String? = null,
            @SerialName("not_authorized_to_access_user")
            val notAuthorizedToAccessUser: String? = null,
            @SerialName("not_authorized_to_access_key")
            val notAuthorizedToAccessKey: String? = null,
            @SerialName("problems_with_your_submission")
            val problemsWithYourSubmission: String? = null,
            @SerialName("you_are_not_authorized_to_access_this_user")
            val youAreNotAuthorizedToAccessThisUser: String? = null,
            @SerialName("customer_with_this_email_address_exists")
            val customerWithThisEmailAddressExists: String? = null,
            @SerialName("customer_register_success")
            val customerRegisterSuccess: String? = null,
            @SerialName("activation_of_trial")
            val activationOfTrial: String? = null,
            @SerialName("merchant_does_not_exist")
            val merchantDoesNotExist: String? = null,
            @SerialName("offer_does_not_exist")
            val offerDoesNotExist: String? = null,
            @SerialName("offer_does_not_exist_or_inactive")
            val offerDoesNotExistOrInactive: String? = null,
            @SerialName("customer_has_already_redeemed_offer_x_the_maximum_allowed_times")
            val customerHasAlreadyRedeemedOfferXTheMaximumAllowedTimes: String? = null,
            @SerialName("customer_does_not_own_offer_x")
            val customerDoesNotOwnOfferX: String? = null,
            @SerialName("offer_x_is_not_available_at_outlet_y")
            val offerXIsNotAvailableAtOutletY: String? = null,
            @SerialName("offer_can_be_redeemed_a_maximum_of_x_times_you_requested_y_times")
            val offerCanBeRedeemedAMaximumOfXTimesYouRequestedYTimes: String? = null,
            @SerialName("no_offers_specified")
            val noOffersSpecified: String? = null,
            @SerialName("offers_must_belong_to_the_same_merchant")
            val offersMustBelongToTheSameMerchant: String? = null,
            @SerialName("invalid_merchant_pin")
            val invalidMerchantPin: String? = null,
            @SerialName("informatica_error")
            val informaticaError: String? = null,
            @SerialName("for_given_key_values_are_not_found")
            val forGivenKeyValuesAreNotFound: String? = null,
            @SerialName("there_is_no_such_record")
            val thereIsNoSuchRecord: String? = null,
            @SerialName("you_are_not_authorized_to_delete_this_customer_devices_record")
            val youAreNotAuthorizedToDeleteThisCustomerDevicesRecord: String? = null,
            @SerialName("there_are_no_customer_devices_belonging_to_the_specified_customer")
            val thereAreNoCustomerDevicesBelongingToTheSpecifiedCustomer: String? = null,
            @SerialName("problems_with_customer_devices_record_deleting")
            val problemsWithCustomerDevicesRecordDeleting: String? = null,
            @SerialName("email_and_key_required")
            val emailAndKeyRequired: String? = null,
            @SerialName("you_are_not_authorized_to_access_this_key")
            val youAreNotAuthorizedToAccessThisKey: String? = null,
            @SerialName("invalid_login_or_password")
            val invalidLoginOrPassword: String? = null,
            @SerialName("invalid_password")
            val invalidPassword: String? = null,
            @SerialName("you_have_successfully_activated_the_key_with_branch")
            val youHaveSuccessfullyActivatedTheKeyWithBranch: String? = null,
            @SerialName("access_restricted_to_application")
            val accessRestrictedToApplication: String? = null,
            @SerialName("you_are_not_allowed_to_access_this_application")
            val youAreNotAllowedToAccessThisApplication: String? = null,
            @SerialName("enquiry_success_message")
            val enquirySuccessMessage: String? = null,
            @SerialName("offer_tag_resuable")
            val offerTagResuable: String? = null,
            @SerialName("offer_tag_promo_code")
            val offerTagPromoCode: String? = null,
            @SerialName("offer_tag_live")
            val offerTagLive: String? = null,
            @SerialName("offer_tag_express")
            val offerTagExpress: String? = null,
            @SerialName("getaways_pre_popup_title")
            val getawaysPrePopupTitle: String? = null,
            @SerialName("travel_section_title")
            val travelSectionTitle: String? = null,
            @SerialName("travel_section_button_title")
            val travelSectionButtonTitle: String? = null,
            @SerialName("getaways_section_title")
            val getawaysSectionTitle: String? = null,
            @SerialName("getaways_section_button_title")
            val getawaysSectionButtonTitle: String? = null,
            @SerialName("quiqup_service_delay_message")
            val quiqupServiceDelayMessage: String? = null,
            @SerialName("verify_email_resend_btn_title")
            val verifyEmailResendBtnTitle: String? = null,
            @SerialName("branch_invalid_key")
            val branchInvalidKey: String? = null,
            @SerialName("branch_used_key")
            val branchUsedKey: String? = null,
            @SerialName("branch_deactivated_key")
            val branchDeactivatedKey: String? = null,
            @SerialName("branch_valid_key")
            val branchValidKey: String? = null,
            @SerialName("welcome")
            val welcome: String? = null,
            @SerialName("customer_with_this_email_address_already_exists")
            val customerWithThisEmailAddressAlreadyExists: String? = null,
            @SerialName("verify_email_sent_message")
            val verifyEmailSentMessage: String? = null,
            @SerialName("password_reset_link_sent_message")
            val passwordResetLinkSentMessage: String? = null,
            @SerialName("verify_email_continue_button_title")
            val verifyEmailContinueButtonTitle: String? = null,
            @SerialName("verify_email_continue_message")
            val verifyEmailContinueMessage: String? = null,
            @SerialName("location_category_hotels")
            val locationCategoryHotels: String? = null,
            @SerialName("otp_is_incorrect")
            val otpIsIncorrect: String? = null,
            @SerialName("otp_message_android")
            val otpMessageAndroid: String? = null,
            @SerialName("otp_resend_successfully")
            val otpResendSuccessfully: String? = null,
            @SerialName("otp_send_successfully")
            val otpSendSuccessfully: String? = null,
            @SerialName("resend_otp_only_works_after_30_seconds")
            val resendOtpOnlyWorksAfter30Seconds: String? = null,
            @SerialName("phone_number_is_required")
            val phoneNumberIsRequired: String? = null,
            @SerialName("promo_code_coupon_apply_discount_btn_text")
            val promoCodeCouponApplyDiscountBtnText: String? = null,
            @SerialName("promo_code_coupon_dismiss_btn_text")
            val promoCodeCouponDismissBtnText: String? = null,
            @SerialName("promo_code_coupon_dismiss_btn_text_color")
            val promoCodeCouponDismissBtnTextColor: String? = null,
            @SerialName("promo_code_coupon_step1_text")
            val promoCodeCouponStep1Text: String? = null,
            @SerialName("promo_code_coupon_step1_title")
            val promoCodeCouponStep1Title: String? = null,
            @SerialName("promo_code_coupon_step2_text")
            val promoCodeCouponStep2Text: String? = null,
            @SerialName("promo_code_coupon_step2_title")
            val promoCodeCouponStep2Title: String? = null,
            @SerialName("promo_code_discount_code_tooltip")
            val promoCodeDiscountCodeTooltip: String? = null,
            @SerialName("promo_code_swipe_redemption_des")
            val promoCodeSwipeRedemptionDes: String? = null,
            @SerialName("promo_code_tooltip_subtitle")
            val promoCodeTooltipSubtitle: String? = null,
            @SerialName("promo_code_tooltip_title")
            val promoCodeTooltipTitle: String? = null
        )

        @Serializable
        data class Location(
            @SerialName("id")
            val id: String? = null,
            @SerialName("name")
            val name: String? = null,
            @SerialName("currency")
            val currency: String? = null
        )

        @Serializable
        data class VisaCategory(
            @SerialName("visa_type")
            val visaType: String? = null,
            @SerialName("visa_type_id")
            val visaTypeId: String? = null
        )

        @Serializable
        data class PasswordRule(
            @SerialName("rule")
            val rule: String? = null,
            @SerialName("type")
            val type: String? = null,
            @SerialName("info_text")
            val infoText: String? = null,
            @SerialName("invalid_icon")
            val invalidIcon: String? = null,
            @SerialName("valid_icon")
            val validIcon: String? = null,
            @SerialName("invalid_text_color")
            val invalidTextColor: String? = null,
            @SerialName("valid_text_color")
            val validTextColor: String? = null
        )
    }
}