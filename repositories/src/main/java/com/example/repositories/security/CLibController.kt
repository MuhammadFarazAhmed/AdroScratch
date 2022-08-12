package com.example.repositories.security

import com.example.domain.BuildConfig


object CLibController {

    init {
        System.loadLibrary("native-blue-lib")
    }

    external fun getGTAKey(key: String): String
    external fun getAppboyKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getRelicKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getMallIQKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getCPKeys(buildVariant: String = BuildConfig.FLAVOR): Array<String>
    external fun getAuSKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getAuSaltKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getPromoAuSKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getPromoAuSaltKey(buildVariant: String = BuildConfig.FLAVOR): String

    external fun getSKey(key: String): String
    external fun getApiToken(key: String): String

    external fun getSRKey(key: String): String
    external fun getJApiToken(key: String): String

    external fun getX(): String
    external fun getUPY(): String
    external fun getUPPY(): String

    //external fun stringFromJNI(buildVariant:String = BuildConfig.FLAVOR): String
    external fun getCoreBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getAuthBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getProfileBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String

    //    external fun getOfferTabBaseUrlOnline(buildVariant:String = BuildConfig.FLAVOR): String
    external fun getFilterBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getOutletBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getMerchantBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getENTBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getConfigBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getGMKey(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getAnalyticsBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getRedemptionBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getSavingBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getBundleUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getReCaptchaUrl(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getRedemptionBundleUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getAnalyticsBundleUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getAnalyticsToken(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getCardUrl(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getKochavaKey(buildVariant: String = BuildConfig.FLAVOR): String

    external fun getPromoCodeBaseUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String
    external fun getPromoCodeBundleUrlOnline(buildVariant: String = BuildConfig.FLAVOR): String

}