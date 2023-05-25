package com.theentertainerme.adro.security

import com.example.adro.BuildConfig

class CLibController {

    companion object {
        init {
            System.loadLibrary("native-blue-lib")
        }
        var instance: CLibController = CLibController()
    }
    external fun getGTAKey(key: String): String
    external fun getAppboyKey(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getRelicKey(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getMallIQKey(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getCPKeys(buildVariant: String = BuildConfig.BUILD_TYPE): Array<String>
    external fun getAuSKey(buildVariant:String = BuildConfig.BUILD_TYPE): String //this one
    external fun getAuSaltKey(buildVariant:String = BuildConfig.BUILD_TYPE): String//this one
    external fun getPromoAuSKey(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getPromoAuSaltKey(buildVariant:String = BuildConfig.BUILD_TYPE): String
    //external fun getU(key: String): String
    //external fun getS(key: String): String //For Signing jwt token using
    //external fun getUP(key: String): String

//    external fun getSKey(key: String): String
//    external fun getApiToken(key: String): String

    external fun getSRKey(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getJApiToken(buildVariant: String = BuildConfig.BUILD_TYPE): String


    external fun getX(): String
    //external fun getCR(): String
    //external fun hide(key : String,value : String): String
    external fun getUPY(): String
    external fun getUPPY(): String

    //external fun stringFromJNI(buildVariant:String = BuildVariantsConstants.BUILD_TYPE): String
    external fun getCoreBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getAuthBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getProfileBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    //    external fun getOfferTabBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getOutletBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getMerchantBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getRecentSearchBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getENTBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getConfigBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getGMKey(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getAnalyticsBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getRedemptionBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getSavingBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getBundleUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getReCaptchaUrl(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getRedemptionBundleUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getAnalyticsBundleUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getAnalyticsToken(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getCardUrl(buildVariant: String = BuildConfig.BUILD_TYPE): String
    external fun getKochavaKey(buildVariant: String = BuildConfig.BUILD_TYPE): String

    external fun getPromoCodeBaseUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String
    external fun getPromoCodeBundleUrlOnline(buildVariant:String = BuildConfig.BUILD_TYPE): String

}