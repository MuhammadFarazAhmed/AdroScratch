#include <jni.h>
#include <string>
//#include "native-blue-lib.h"
#define TABLELEN        64
#define BUFFFERLEN      128
#define ENCODERLEN      4
#define ENCODERBLOCKLEN 3
#define PADDINGCHAR     '='
#define BASE64CHARSET   "ABCDEFGHIJKLMNOPQRSTUVWXYZ"\
                        "abcdefghijklmnopqrstuvwxyz"\
                        "0123456789"\
                        "+/";

static const char *DEVNODE = "devnode";
static const char *QANODE = "qanode";
static const char *UATNODE = "uatnode";
static const char *RCNODE ="rcnode";
static const char *PRODUCTION = "production";

/********************************* DEV *****************************************************/

/*
 *    Outlet: apidvb2betotlsrvpy.etenvbiz.com
 *    Config: apidvb2betcfgsrvpy.etenvbiz.com
 *    Merchant: apidvb2betmrchtsrvpy.etenvbiz.com
 *    User : apidvb2betusrsrvpy.etenvbiz.com     -> DEV_AUTH_BASE_URL aka AUthentication , profile
 *    Redemption: apidvb2betrdmpnsrvpy.etenvbiz.com
 *    ENT : apidvb2betentsrvpy.etenvbiz.com  ->DEV_ENT_BASE_URL aka HOME
 * */
//https://apidvb2betotlsrvpy.etenvbiz.com/ets_api/v3/outlets
/*------------------------NEW URLS----------------------------------*/
const char *DEV_OUTLET_BASE_URL = "https://apidvb2betotlsrvpy.etenvbiz.com/ets_api/v5/";
const char *DEV_MERCHANT_BASE_URL = "https://apidvb2betmrchtsrvpy.etenvbiz.com/ets_api/v5/";
const char *DEV_FILTER_BASE_URL = "https://apidvb2betcfgsrvpy.etenvbiz.com/ets_api/v5/";
const char *DEV_ENT_BASE_URL = "https://apidvb2betentsrvpy.etenvbiz.com/ets_api/v5/";
const char *DEV_REDEMPTION_ADRO_BASE_URL = "https://apidvb2betrdmpnsrvpy.etenvbiz.com/ets_api/v5/";
const char *DEV_AUTH_BASE_URL = "https://apidvb2betusrsrvpy.etenvbiz.com/et_user/v5/";
const char *DEV_PROFILE_ADRO_BASE_URL = "https://apiqab2cpyusrpref.etenvbiz.com/api_ets/v1/";

/*------------------------NEW URLS----------------------------------*/


const char *DEV_CORE_BASE_URL = "https://apidvb2baldpy.etenvbiz.com/";
//const char *DEV_CONFIG_BASE_URL = "https://apidvb2betcfgsrvpy.etenvbiz.com/";

/*Config base url and bundle is old*/
const char *DEV_CONFIG_BASE_URL = "https://apidvb2baldpy.etenvbiz.com/";
const char *DEV_BUNDLE_URL = "api_adr/v2/";

//const char *DEV_BUNDLE_URL = "et_user/v5/";

const char *DEV_REDEMPTION_BASE_URL = "https://redemptiondvsvr.etenvbiz.com/";
const char *DEV_REDEMPTION_BUNDLE_URL = "api_ets/v1/";

const char *DEV_ANALYTICS_BASE_URL = "https://apidvb2baldpy.etenvbiz.com/";
const char *DEV_ANALYTICS_BUNDLE_URL = "";

/**************************************************************************************/

/*------------------------NEW URLS----------------------------------*/
const char *QA_OUTLET_BASE_URL = "https://apiqab2betotlsrvpy.etenvbiz.com/ets_api/v5/";
const char *QA_MERCHANT_BASE_URL = "https://apiqab2betmrchtsrvpy.etenvbiz.com/ets_api/v5/";
const char *QA_FILTER_BASE_URL = "https://apiqab2betcfgsrvpy.etenvbiz.com/ets_api/v5/";
const char *QA_ENT_BASE_URL = "https://apiqab2betentsrvpy.etenvbiz.com/ets_api/v5/";
const char *QA_REDEMPTION_ADRO_BASE_URL = "https://apiqab2betrdmpnsrvpy.etenvbiz.com/ets_api/v5/";
const char *QA_SAVING_ADRO_BASE_URL = "https://apiqab2betusrsrvpy.etenvbiz.com/et_user/v5/";
const char *QA_AUTH_BASE_URL = "https://apiqab2betusrsrvpy.etenvbiz.com/et_user/v5/";
const char *QA_PROFILE_ADRO_BASE_URL = "https://apiqab2cpyusrpref.etenvbiz.com/api_ets/v1/";



/*------------------------NEW URLS----------------------------------*/

//const char *QA_AUTH_BASE_URL = "https://apiqab2baldpy.etenvbiz.com/";
const char *QA_CORE_BASE_URL = "https://apiqab2baldpy.etenvbiz.com/";
const char *QA_CONFIG_BASE_URL = "https://apiqab2baldpy.etenvbiz.com/";
const char *QA_BUNDLE_URL = "api_adr/v2/";
const char *QA_REDEMPTION_BASE_URL = "https://redemptionqasvr.etenvbiz.com/";
const char *QA_REDEMPTION_BUNDLE_URL = "api_ets/v1/";
const char *QA_ANALYTICS_BASE_URL = "https://apiqab2baldpy.etenvbiz.com/";
const char *QA_ANALYTICS_BUNDLE_URL = "";


/*--------------------------------------------------------------------*/

//        ET_Config_Service
//apiutb2betcfgsrvpy.theentertainerme.com
//        ET_Ent_Service
//apiutb2betentsrvpy.theentertainerme.com
//        ET_Merchant_Service
//apiutb2betmrchtsrvpy.theentertainerme.com
//        ET_Outlet_Service
//apiutb2betotlsrvpy.theentertainerme.com
//        ET_Redemption_Service
//apiutb2betrdmpnsrvpy.theentertainerme.com
//        ET_User_Service
//apiutb2betusrsrvpy.theentertainerme.com

/*--------------------------------------------------------------------*/
//NEW URL
const char *UAT_OUTLET_BASE_URL = "apiutb2betotlsrvpy.theentertainerme.com/ets_api/v5/";
const char *UAT_MERCHANT_BASE_URL = "apiutb2betmrchtsrvpy.theentertainerme.com/ets_api/v5/";
const char *UAT_FILTER_BASE_URL = "apiutb2betcfgsrvpy.theentertainerme.com/ets_api/v5/";
const char *UAT_ENT_BASE_URL = "apiutb2betentsrvpy.theentertainerme.com";
const char *UAT_REDEMPTION_ADRO_BASE_URL = "apiutb2betrdmpnsrvpy.theentertainerme.com/ets_api/v5/";
const char *UAT_SAVING_ADRO_BASE_URL = "apiutb2betusrsrvpy.theentertainerme.com";
const char *UAT_AUTH_BASE_URL = "apiutb2betusrsrvpy.theentertainerme.com";
const char *UAT_PROFILE_ADRO_BASE_URL = "apiutb2cpyusrpref.theentertainerme.com/api_ets/v1/";

/*--------------------------------------------------------------------*/


//const char *UAT_AUTH_BASE_URL = "https://apiutb2baldpy.theentertainerme.com";
const char *UAT_CORE_BASE_URL = "https://apiutb2baldpy.theentertainerme.com/";
const char *UAT_CONFIG_BASE_URL = "https://apiutb2baldpy.theentertainerme.com/";
const char *UAT_BUNDLE_URL = "api_adr/v2/";
const char *UAT_REDEMPTION_BASE_URL = "https://apiutb2brdmsrvrpy.theentertainerme.com/";
const char *UAT_REDEMPTION_BUNDLE_URL = "api_ets/v1/";
const char *UAT_ANALYTICS_BASE_URL = "https://391802pmmf.execute-api.eu-west-1.amazonaws.com/";
const char *UAT_ANALYTICS_BUNDLE_URL = "UAT/v2/";

//**************************RC URL******************************************

/*
 *
config servvice:
apircb2betcfgsrvpy.theentertainerme.com
home service:
apircb2betentsrvpy.theentertainerme.com
merchant service:
apircb2betmrchtsrvpy.theentertainerme.com
outlet:
apircb2betotlsrvpy.theentertainerme.com
redemption:
apircb2betrdmpnsrvpy.theentertainerme.com
user:
apircb2betusrsrvpy.theentertainerme.com

 * */

//**************************************************************************

const char *RC_OUTLET_BASE_URL = "https://apircb2betotlsrvpy.theentertainerme.com/ets_api/v5/";
const char *RC_MERCHANT_BASE_URL = "https://apircb2betmrchtsrvpy.theentertainerme.com/ets_api/v5/";
const char *RC_FILTER_BASE_URL = "https://apircb2betcfgsrvpy.theentertainerme.com/ets_api/v5/";
const char *RC_ENT_BASE_URL = "apircb2betentsrvpy.theentertainerme.com/";
const char *RC_REDEMPTION_ADRO_BASE_URL = "https://apircb2betrdmpnsrvpy.theentertainerme.com/ets_api/v5/";
const char *RC_SAVING_ADRO_BASE_URL = "https://apircb2betusrsrvpy.theentertainerme.com/et_user/v5/";
const char *RC_AUTH_BASE_URL = "https://apircb2betusrsrvpy.theentertainerme.com/et_user/v5/";
const char *RC_PROFILE_ADRO_BASE_URL = "https://apircb2cpyusrpref.theentertainerme.com/api_ets/v1/";


//**************************RC URL***************************************



//const char *RC_AUTH_BASE_URL = "https://apircb2baldpy.theentertainerme.com/";
const char *RC_CORE_BASE_URL = "https://apircb2baldpy.theentertainerme.com/";
//const char *RC_PROFILE_ADRO_BASE_URL = "https://apiqab2cpyusrpref.etenvbiz.com/api_ets/v1/";
const char *RC_CONFIG_BASE_URL = "https://apircb2baldpy.theentertainerme.com/";
const char *RC_BUNDLE_URL = "api_adr/v2/";
const char *RC_REDEMPTION_BASE_URL = "https://apipdb2balftpy.theentertainerme.com/";
const char *RC_REDEMPTION_BUNDLE_URL = "redemption/v2/";
const char *RC_ANALYTICS_BASE_URL = "https://jb0auve5he.execute-api.eu-west-1.amazonaws.com/";
const char *RC_ANALYTICS_BUNDLE_URL = "PROD/v2/";


//*************************LIVE**********************************************
//config servvice:
//apiprdb2betcfgsrvpy.theentertainerme.com
//        home service:
//apiprdb2betentsrvpy.theentertainerme.com
//        merchant service:
//apiprdb2betmrchtsrvpy.theentertainerme.com
//        outlet:
//apiprdb2betotlsrvpy.theentertainerme.com
//        redemption:
//apiprdb2betrdmpnsrvpy.theentertainerme.com
//        user:
//apiprdb2betusrsrvpy.theentertainerme.com
//*************************LIVE**********************************************

const char *PRODUCTION_OUTLET_BASE_URL = "https://apiprdb2betotlsrvpy.theentertainerme.com/ets_api/v5/";
const char *PRODUCTION_MERCHANT_BASE_URL = "https://apiprdb2betmrchtsrvpy.theentertainerme.com/ets_api/v5/";
const char *PRODUCTION_FILTER_BASE_URL = "https://apiprdb2betcfgsrvpy.theentertainerme.com/ets_api/v5/";
const char *PRODUCTION_ENT_BASE_URL = "apiprdb2betentsrvpy.theentertainerme.com";
const char *PRODUCTION_REDEMPTION_ADRO_BASE_URL = "https://apiprdb2betrdmpnsrvpy.theentertainerme.com/ets_api/v5/";
const char *PRODUCTION_SAVING_ADRO_BASE_URL = "https://apiprdb2betusrsrvpy.theentertainerme.com/et_user/v5/";
const char *PRODUCTION_AUTH_BASE_URL = "https://apiprdb2betusrsrvpy.theentertainerme.com/et_user/v5/";
const char *PRODUCTION_PROFILE_ADRO_BASE_URL = "https://apipdb2cpyusrpref.theentertainerme.com/api_ets/v1/";

//*************************LIVE**********************************************


//const char *PRODUCTION_AUTH_BASE_URL = "https://apipdb2baldpy.theentertainerme.com/";
const char *PRODUCTION_CORE_BASE_URL = "https://apipdb2baldpy.theentertainerme.com/";
//const char *PRODUCTION_PROFILE_ADRO_BASE_URL = "https://apiqab2cpyusrpref.etenvbiz.com/api_ets/v1/";
const char *PRODUCTION_CONFIG_BASE_URL = "https://apipdb2baldpy.theentertainerme.com/";
const char *PRODUCTION_BUNDLE_URL = "api_adr/v2/";
const char *PRODUCTION_REDEMPTION_BASE_URL = "https://apipdb2balftpy.theentertainerme.com/";
const char *PRODUCTION_REDEMPTION_BUNDLE_URL = "redemption/v2/";
const char *PRODUCTION_ANALYTICS_BASE_URL = "https://jb0auve5he.execute-api.eu-west-1.amazonaws.com/";
const char *PRODUCTION_ANALYTICS_BUNDLE_URL = "PROD/v2/";


const char *DEV_CAPCTHA_URL = "https://dventcart.etenvbiz.com/captcha/b2b-captcha.php";
const char *QA_CAPCTHA_URL = "https://entqacart.etenvbiz.com/captcha/b2b-captcha.php";
const char *UAT_CAPCTHA_URL = "https://entcartut.theentertainerme.com/captcha/b2b-captcha.php";
const char *RC_CAPCTHA_URL = "https://theentertainerme.com/captcha/b2b-captcha.php";
const char *PRODUCTION_CAPCTHA_URL = "https://theentertainerme.com/captcha/b2b-captcha.php";


const char *DEV_CARD_URL = "";
const char *QA_CARD_URL = "https://entaldarweb.etenvbiz.com/cards-webview";
const char *UAT_CARD_URL = "https://eutaldrweb.theentertainerme.com/cards-webview";
const char *RC_CARD_URL = "https://etrcaldrweb.theentertainerme.com/cards-webview";
const char *PRODUCTION_CARD_URL = "https://www.darnarewards.com/cards-webview";

const char *DEV_PROMO_CODE_BASE_URL = "https://dventapi.etenvbiz.com/";
const char *QA_PROMO_CODE_BASE_URL = "https://entqaapi.etenvbiz.com/";
const char *UAT_PROMO_CODE_BASE_URL = "https://entutapi.theentertainerme.com/";
const char *RC_PROMO_CODE_BASE_URL = "https://rcapi.theentertainerme.com/";
const char *PRODUCTION_PROMO_CODE_BASE_URL = "https://api.theentertainerme.com/";

const char *DEV_PROMO_CODE_BUNDLE_URL = "et_rs_prd/web/v70/wl/";
const char *QA_PROMO_CODE_BUNDLE_URL = "et_rs_prd/web/v70/wl/";
const char *UAT_PROMO_CODE_BUNDLE_URL = "et_rs_prd/web/v70/wl/";
const char *RC_PROMO_CODE_BUNDLE_URL = "et_rs_prd/web/v70/wl/";
const char *PRODUCTION_PROMO_CODE_BUNDLE_URL = "et_rs_prd/web/v70/wl/";


const char *DEV_APPBOY_KEY = "85ee5070-ee71-4eb5-9f8d-b61fa9de961b";
const char *QA_APPBOY_KEY = "85ee5070-ee71-4eb5-9f8d-b61fa9de961b";
const char *UAT_APPBOY_KEY = "85ee5070-ee71-4eb5-9f8d-b61fa9de961b";
const char *RC_APPBOY_KEY = "e372a4c1-6530-41fb-b9cb-94afc5c4b951";
const char *PRODUCTION_APPBOY_KEY = "e372a4c1-6530-41fb-b9cb-94afc5c4b951";

const char *DEV_KOCHAVA_KEY = "kostaging-aldar-darna-android-msb";
const char *QA_KOCHAVA_KEY = "kostaging-aldar-darna-android-msb";
const char *UAT_KOCHAVA_KEY = "kostaging-aldar-darna-android-msb";
const char *RC_KOCHAVA_KEY = "koaldar-darna-android-oxc";
const char *PRODUCTION_KOCHAVA_KEY = "koaldar-darna-android-oxc";

const char *DEV_MALLIQ_KEY = "93752903994031081";
const char *QA_MALLIQ_KEY = "93752903994031081";
const char *UAT_MALLIQ_KEY = "93752903994031081";
const char *RC_MALLIQ_KEY = "93752903994031081";
const char *PRODUCTION_MALLIQ_KEY = "93752903994031081";

const char *DEV_ANALYTICS_TOKEN = "";
const char *QA_ANALYTICS_TOKEN = "";
const char *UAT_ANALYTICS_TOKEN = "6ab7e131543a37ac89f7ce8993c9b3ce";
const char *RC_ANALYTICS_TOKEN = "d44335434557f59bf52e55bfa4b77708";
const char *PRODUCTION_ANALYTICS_TOKEN = "d44335434557f59bf52e55bfa4b77708";

const char *DEV_RELIC_KEY = "AA6b2e04d5e512dfab56a0d16120becad4cf25b4a3-NRMA";
const char *QA_RELIC_KEY = "AA6b2e04d5e512dfab56a0d16120becad4cf25b4a3-NRMA";
const char *UAT_RELIC_KEY = "AA6b2e04d5e512dfab56a0d16120becad4cf25b4a3-NRMA";
const char *RC_RELIC_KEY = "AA6b2e04d5e512dfab56a0d16120becad4cf25b4a3-NRMA";
const char *PRODUCTION_RELIC_KEY = "AAb61f7e91f3bdcd87a174616bc0901968c088d2b9-NRMA";

/* SAME KEY IS ADDED FOR EVERY VARAINT*/
const char *DEV_GOOGLE_KEY = "AIzaSyC3C6-250Ht5zsiGkR0gg2lZpdEuTzBwoM";
const char *QA_GOOGLE_KEY = "AIzaSyC3C6-250Ht5zsiGkR0gg2lZpdEuTzBwoM";
const char *UAT_GOOGLE_KEY = "AIzaSyC3C6-250Ht5zsiGkR0gg2lZpdEuTzBwoM";
const char *RC_GOOGLE_KEY = "AIzaSyC3C6-250Ht5zsiGkR0gg2lZpdEuTzBwoM";
const char *PRODUCTION_GOOGLE_KEY = "AIzaSyC3C6-250Ht5zsiGkR0gg2lZpdEuTzBwoM";

static unsigned char user[] = "ce%&@8h1cxf&#";

int decodeblock(char *input, char *output, int oplen);

int Base64Decode(const char *input, char *output, int oplen);

void xorValueWithKey(const char *value, char *xorOutput);

void decodeString(const char *value, char *outPut);

int Base64Encode(const char *input, char *output, int oplen);

int encodeblock(char *input, char *output, int oplen);

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getCoreBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                            jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_CORE_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_CORE_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_CORE_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_CORE_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_CORE_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_CORE_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getGMKey(JNIEnv *env, jobject /* this */,
                                                                jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_GOOGLE_KEY);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_GOOGLE_KEY);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_GOOGLE_KEY);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_GOOGLE_KEY);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_GOOGLE_KEY);
    else
        return env->NewStringUTF(PRODUCTION_GOOGLE_KEY);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getPromoCodeBaseUrlOnline(JNIEnv* env, jobject /* this */,
                                                                                 jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_PROMO_CODE_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_PROMO_CODE_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_PROMO_CODE_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_PROMO_CODE_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_PROMO_CODE_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_PROMO_CODE_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getRedemptionBaseUrlOnline(JNIEnv* env, jobject /* this */,
                                                                                  jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_REDEMPTION_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_REDEMPTION_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_REDEMPTION_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_REDEMPTION_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
//        return env->NewStringUTF(DEV_REDEMPTION_BASE_URL);
        return env->NewStringUTF(DEV_REDEMPTION_ADRO_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_REDEMPTION_ADRO_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getSavingBaseUrlOnline(JNIEnv* env, jobject /* this */,
                                                                              jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_SAVING_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_SAVING_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_SAVING_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_SAVING_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
//        return env->NewStringUTF(DEV_REDEMPTION_BASE_URL);
        return env->NewStringUTF(DEV_REDEMPTION_ADRO_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_SAVING_ADRO_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAuthBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                            jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_AUTH_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_AUTH_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_AUTH_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_AUTH_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_AUTH_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_AUTH_BASE_URL);
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getProfileBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                               jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_PROFILE_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_PROFILE_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_PROFILE_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_PROFILE_ADRO_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_PROFILE_ADRO_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_PROFILE_ADRO_BASE_URL);
}

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_example_adro_security_CLibController_getOfferTabBaseUrlOnline(JNIEnv *env, jobject /* this */,
//                                                                              jstring buildVarent) {
//    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
//    if (strcmp(buildVarentStr, PRODUCTION) == 0)
//        return env->NewStringUTF("");
//    else if (strcmp(buildVarentStr, QANODE) == 0)
//        return env->NewStringUTF("");
//    else if (strcmp(buildVarentStr, RCNODE) == 0)
//        return env->NewStringUTF("");
//    else if (strcmp(buildVarentStr, UATNODE) == 0)
//        return env->NewStringUTF("");
//    else if (strcmp(buildVarentStr, DEVNODE) == 0)
//        return env->NewStringUTF(DEV_OFFER_TAB_BASE_URL);
//    else
//        return env->NewStringUTF("");
//}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getFilterBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                              jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_FILTER_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_FILTER_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_FILTER_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_FILTER_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_FILTER_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_FILTER_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getOutletBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                              jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_OUTLET_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_OUTLET_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_OUTLET_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_OUTLET_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_OUTLET_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_OUTLET_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getMerchantBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                                jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_MERCHANT_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_MERCHANT_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_MERCHANT_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_MERCHANT_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_MERCHANT_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_MERCHANT_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getENTBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                           jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_ENT_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_ENT_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_ENT_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_ENT_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_ENT_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_ENT_BASE_URL);
}




extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getConfigBaseUrlOnline(JNIEnv *env, jobject /* this */,
                                                                              jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_CONFIG_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_CONFIG_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_CONFIG_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_CONFIG_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_CONFIG_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_CONFIG_BASE_URL);
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getBundleUrlOnline(JNIEnv *env, jobject /* this */,
                                                                          jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_BUNDLE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_BUNDLE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_BUNDLE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_BUNDLE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_BUNDLE_URL);
    else
        return env->NewStringUTF(PRODUCTION_BUNDLE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getPromoCodeBundleUrlOnline(JNIEnv *env, jobject /* this */,
                                                                                   jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_PROMO_CODE_BUNDLE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_PROMO_CODE_BUNDLE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_PROMO_CODE_BUNDLE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_PROMO_CODE_BUNDLE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_PROMO_CODE_BUNDLE_URL);
    else
        return env->NewStringUTF(PRODUCTION_PROMO_CODE_BUNDLE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getRedemptionBundleUrlOnline(JNIEnv *env, jobject /* this */,
                                                                                    jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_REDEMPTION_BUNDLE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_REDEMPTION_BUNDLE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_REDEMPTION_BUNDLE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_REDEMPTION_BUNDLE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_REDEMPTION_BUNDLE_URL);
    else
        return env->NewStringUTF(PRODUCTION_REDEMPTION_BUNDLE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAnalyticsBaseUrlOnline(JNIEnv* env, jobject /* this */,
                                                                                 jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_ANALYTICS_BASE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_ANALYTICS_BASE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_ANALYTICS_BASE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_ANALYTICS_BASE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_ANALYTICS_BASE_URL);
    else
        return env->NewStringUTF(PRODUCTION_ANALYTICS_BASE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAnalyticsBundleUrlOnline(JNIEnv *env, jobject /* this */,
                                                                                   jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_ANALYTICS_BUNDLE_URL);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_ANALYTICS_BUNDLE_URL);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_ANALYTICS_BUNDLE_URL);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_ANALYTICS_BUNDLE_URL);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_ANALYTICS_BUNDLE_URL);
    else
        return env->NewStringUTF(PRODUCTION_ANALYTICS_BUNDLE_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAnalyticsToken(JNIEnv *env, jobject /* this */,
                                                                         jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_ANALYTICS_TOKEN);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_ANALYTICS_TOKEN);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_ANALYTICS_TOKEN);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_ANALYTICS_TOKEN);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_ANALYTICS_TOKEN);
    else
        return env->NewStringUTF(PRODUCTION_ANALYTICS_TOKEN);
}


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getGTAKey(JNIEnv *env, jobject thiz, jstring key){
    char *nativeString = "afr41olwerplk56d53hjqpas6yst7642shskasd7511";

    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(nativeString);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAppboyKey(JNIEnv *env, jobject /* this */,
                                                                    jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_APPBOY_KEY);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_APPBOY_KEY);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_APPBOY_KEY);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_APPBOY_KEY);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_APPBOY_KEY);
    else
        return env->NewStringUTF(PRODUCTION_APPBOY_KEY);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getKochavaKey(JNIEnv *env, jobject /* this */,
                                                                     jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_KOCHAVA_KEY);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_KOCHAVA_KEY);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_KOCHAVA_KEY);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_KOCHAVA_KEY);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_KOCHAVA_KEY);
    else
        return env->NewStringUTF(PRODUCTION_KOCHAVA_KEY);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getRelicKey(JNIEnv *env, jobject /* this */,
                                                                   jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_RELIC_KEY);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_RELIC_KEY);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_RELIC_KEY);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_RELIC_KEY);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_RELIC_KEY);
    else
        return env->NewStringUTF(PRODUCTION_RELIC_KEY);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getMallIQKey(JNIEnv *env, jobject /* this */,jstring buildVarent) {
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF(PRODUCTION_MALLIQ_KEY);
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF(QA_MALLIQ_KEY);
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF(RC_MALLIQ_KEY);
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF(UAT_MALLIQ_KEY);
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF(DEV_MALLIQ_KEY);
    else
        return env->NewStringUTF(PRODUCTION_MALLIQ_KEY);
}


extern "C" JNIEXPORT jobjectArray JNICALL
Java_com_example_adro_security_CLibController_getCPKeys(JNIEnv* env, jobject thiz,jstring buildVarent){
    jobjectArray ret;
    int i;
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);

    const int size = 3;
    char *message[size]= {
            "sha256/0ovBbqe9WhsuNP6Z+lstyovizChMupD4zZnb15qB8O4=", //Certificate of etenvbiz
            "sha256/wg0jwOWtsAcNzPNBXYSVscWLX5PuJWQbTKm5kTVuNM0=", //Certificate of Dev/Qa
            "sha256/dQOrVbsrB+UhnnJ2/JfUG08vNSl+d0cqaq5a+lgjUfI=", //Certificate of Uat/Rc/Production
    };
    ret= (jobjectArray)env->NewObjectArray(size,env->FindClass("java/lang/String"),env->NewStringUTF(""));
    for(i=0;i<size;i++) {
        env->SetObjectArrayElement(ret,i,env->NewStringUTF(message[i]));
    }
    return (ret);

}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getCardUrl(JNIEnv* env, jobject /* this */, jstring buildVarent){
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0)
        return env->NewStringUTF(PRODUCTION_CARD_URL);
    if(strcmp(buildVarentStr,RCNODE)==0)
        return env->NewStringUTF(RC_CARD_URL);
    else if(strcmp(buildVarentStr,UATNODE)==0)
        return env->NewStringUTF(UAT_CARD_URL);
    else if(strcmp(buildVarentStr,QANODE)==0)
        return env->NewStringUTF(QA_CARD_URL);
    else if(strcmp(buildVarentStr,DEVNODE)==0)
        return env->NewStringUTF(DEV_CARD_URL);

    else
        return env->NewStringUTF(PRODUCTION_CAPCTHA_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getReCaptchaUrl(JNIEnv* env, jobject /* this */, jstring buildVarent){
    const char *buildVarentStr = env->GetStringUTFChars(buildVarent, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0)
        return env->NewStringUTF(PRODUCTION_CAPCTHA_URL);
    if(strcmp(buildVarentStr,RCNODE)==0)
        return env->NewStringUTF(RC_CAPCTHA_URL);
    else if(strcmp(buildVarentStr,UATNODE)==0)
        return env->NewStringUTF(UAT_CAPCTHA_URL);
    else if(strcmp(buildVarentStr,QANODE)==0)
        return env->NewStringUTF(QA_CAPCTHA_URL);
    else if(strcmp(buildVarentStr,DEVNODE)==0)
        return env->NewStringUTF(DEV_CAPCTHA_URL);

    else
        return env->NewStringUTF(PRODUCTION_CAPCTHA_URL);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_stringFromJNI(
        JNIEnv *env,
        jobject /* this */, jstring buildVarent) {
    return buildVarent;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getU(JNIEnv *env, jobject, jstring key) {
    const char *nativeString = "e1YuH1FyZ2JvdE4=";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getUP(JNIEnv *env, jobject, jstring key) {
    const char *nativeString = "KBV3cAkrOiMwKB8pJ00WZCZmXTJ4ZzE=";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getCR(JNIEnv *env, jobject thiz, jstring key) {
    const char *nativeString = "fkdxIlUqZyAtex50f1QUZHBu";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getSKey(JNIEnv *env, jobject thiz, jstring key) {
    const char *nativeString = "BBFcVSpPBgkHQBUfEWgHA0dANUwCQgkLE1VQaFENF1MzUhtZ";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getApiToken(JNIEnv *env, jobject thiz, jstring key) {
    const char *nativeString = "042a83f1-c2ec-40e6-a0e0-cadbfec8f125";
    return env->NewStringUTF(nativeString);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getSRKey(JNIEnv *env, jobject thiz, jstring buildVaraint) {
    const char *nativeString = "!EyFde4#$%gYsRct54fy@#$5";
    // return env->NewStringUTF(nativeString);

    const char *buildVarentStr = env->GetStringUTFChars(buildVaraint, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF("");
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF("!EyFde4#$%gYsRct54fy@#$5");
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF("!EyFde4#$%gYsRct54fy@#$5");
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF("!EyFde4#$%gYsRct54fy@#$5");
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF("!EyFde4#$%gYsRct54fy@#$5");
    else{
        return env->NewStringUTF("");
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getJApiToken(JNIEnv *env, jobject thiz, jstring buildVaraint) {
    const char *nativeString = "k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4$x";
    //return env->NewStringUTF(nativeString);

    const char *buildVarentStr = env->GetStringUTFChars(buildVaraint, 0);
    if (strcmp(buildVarentStr, PRODUCTION) == 0)
        return env->NewStringUTF("");
    else if (strcmp(buildVarentStr, QANODE) == 0)
        return env->NewStringUTF("k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4$x");
    else if (strcmp(buildVarentStr, RCNODE) == 0)
        return env->NewStringUTF("k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4$x");
    else if (strcmp(buildVarentStr, UATNODE) == 0)
        return env->NewStringUTF("k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4$x");
    else if (strcmp(buildVarentStr, DEVNODE) == 0)
        return env->NewStringUTF("k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4$x");
    else{
        return env->NewStringUTF("");
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getS(JNIEnv *env, jobject thiz, jstring key) {
    const char *nativeString = "";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_hide(JNIEnv *env, jobject thiz, jstring key, jstring value) {
    const char *nativeString = env->GetStringUTFChars(value, 0);

    char xorOutput[BUFFFERLEN + 1] = "";

    xorValueWithKey(nativeString, xorOutput);

    char encodedoutput[BUFFFERLEN + 1] = "";

    Base64Encode(xorOutput, encodedoutput, BUFFFERLEN);
//    char *output = "";
//    encryptDecrypt(encodedoutput,output);
    env->ReleaseStringUTFChars(value, nativeString);

    return env->NewStringUTF(encodedoutput);

}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAuSKey(JNIEnv *env, jobject thiz, jstring key) {
    const char *buildVarentStr = env->GetStringUTFChars(key, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0) {
        const char *nativeString = "VFFEFyNdClRUSlBCQjABAUQTc1pQB1EZB0IVNlNVRB4=";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    } else if(strcmp(buildVarentStr,RCNODE)==0) {
        const char *nativeString = "VFFEFyNdClRUSlBCQjABAUQTc1pQB1EZB0IVNlNVRB4=";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    } else{
        const char *nativeString = "Ul1HHiMBDVdXT1VDETFRU0YVIw1eUAFIBUQRYlRURkQ=";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getAuSaltKey(JNIEnv *env, jobject thiz, jstring key) {
    const char *buildVarentStr = env->GetStringUTFChars(key, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0) {
        const char *nativeString = "BQETFCZcXgJaS14XGjMUNA==";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    } else if(strcmp(buildVarentStr,RCNODE)==0) {
        const char *nativeString = "BQETFCZcXgJaS14XGjMUNA==";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    } else{
        const char *nativeString = "Ul1HHiMBDVdXT1VDETFRUw==";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    }
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getPromoAuSKey(JNIEnv *env, jobject thiz, jstring key) {
    const char *buildVarentStr = env->GetStringUTFChars(key, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0) {
        const char *nativeString = "18b8c9ef473e2126c3c56ab0cb2b71cb";
        return env->NewStringUTF(nativeString);
    } else if(strcmp(buildVarentStr,RCNODE)==0) {
        const char *nativeString = "18b8c9ef473e2126c3c56ab0cb2b71cb";
        return env->NewStringUTF(nativeString);
    } else{
        const char *nativeString = "Ul1HHiMBDVdXT1VDETFRU0YVIw1eUAFIBUQRYlRURkQ=";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    }
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getPromoAuSaltKey(JNIEnv *env, jobject thiz, jstring key) {
    const char *buildVarentStr = env->GetStringUTFChars(key, 0);
    if(strcmp(buildVarentStr,PRODUCTION)==0) {
        const char *nativeString = "18b8c9ef473e2126";
        return env->NewStringUTF(nativeString);
    } else if(strcmp(buildVarentStr,RCNODE)==0) {
        const char *nativeString = "18b8c9ef473e2126";
        return env->NewStringUTF(nativeString);
    } else{
        const char *nativeString = "Ul1HHiMBDVdXT1VDETFRUw==";
        char xorOutput[BUFFFERLEN + 1] = "";
        decodeString(nativeString, xorOutput);
        return env->NewStringUTF(xorOutput);
    }
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getX(JNIEnv *env, jobject thiz) {
    char *nativeString = "E0FOByNdJAcG";
    char xorOutput[BUFFFERLEN + 1] = "";
    decodeString(nativeString, xorOutput);
    return env->NewStringUTF(xorOutput);
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getUPY(JNIEnv *env, jobject thiz) {
    const char *nativeString = "nfDlZWgVNJfOlofA";
    return env->NewStringUTF(nativeString);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_adro_security_CLibController_getUPPY(JNIEnv *env, jobject thiz) {
    char *nativeString = "SF?e(3VdCMW%<t>&K+Jm!#b~Qj*}yA]P";
    return env->NewStringUTF(nativeString);
}

int Base64Encode(const char *input, char *output, int oplen) {
    int rc = 0;
    int index = 0, ipindex = 0, iplen = 0;
    char encoderinput[ENCODERBLOCKLEN + 1] = "";

    iplen = strlen(input);
    while (ipindex < iplen) {
        for (index = 0; index < 3; index++) {
            if (ipindex < iplen) {
                encoderinput[index] = input[ipindex];
            } else {
                encoderinput[index] = 0;
            }
            ipindex++;
        }
        rc = encodeblock(encoderinput, output, oplen);
    }
    return rc;

}

int encodeblock(char *input, char *output, int oplen) {

    int rc = 0, iplen = 0;
    char encodedstr[ENCODERLEN + 1] = "";
    char encodingtabe[TABLELEN + 1] = BASE64CHARSET;

    iplen = strlen(input);
    encodedstr[0] = encodingtabe[input[0] >> 2];
    encodedstr[1] = encodingtabe[((input[0] & 0x03) << 4) |
                                 ((input[1] & 0xf0) >> 4)];
    encodedstr[2] = (iplen > 1 ? encodingtabe[((input[1] & 0x0f) << 2) |
                                              ((input[2] & 0xc0) >> 6)] : PADDINGCHAR);
    encodedstr[3] = (iplen > 2 ? encodingtabe[input[2] & 0x3f] : PADDINGCHAR);
    strncat(output, encodedstr, oplen - strlen(output));

    return rc;
}


void decodeString(const char *value, char *outPut) {
    char decodedOutput[BUFFFERLEN + 1] = "";
    Base64Decode(value, decodedOutput, BUFFFERLEN);
    xorValueWithKey(decodedOutput, outPut);
}

void xorValueWithKey(const char *value, char *xorOutput) {
    int i = 0;
    while (value[i] != '\0') {
        int offset = i % sizeof(user);
        xorOutput[i] = value[i] ^ user[offset];
        i++;
    }
}

int Base64Decode(const char *input, char *output, int oplen) {
    char *charval = 0;
    char decoderinput[ENCODERLEN + 1] = "";
    char encodingtabe[TABLELEN + 1] = BASE64CHARSET;
    int index = 0, asciival = 0, computeval = 0, iplen = 0, rc = 0;
    iplen = strlen(input);
    while (index < iplen) {
        asciival = (int) input[index];
        if (asciival == PADDINGCHAR) {
            rc = decodeblock(decoderinput, output, oplen);
            break;
        } else {
            charval = strchr(encodingtabe, asciival);
            if (charval) {
                decoderinput[computeval] = charval - encodingtabe;
                computeval = (computeval + 1) % 4;
                if (computeval == 0) {
                    rc = decodeblock(decoderinput, output, oplen);
                    decoderinput[0] = decoderinput[1] =
                    decoderinput[2] = decoderinput[3] = 0;
                }
            }
        }
        index++;
    }
    return rc;
}

int decodeblock(char *input, char *output, int oplen) {
    int rc = 0;
    char decodedstr[ENCODERLEN + 1] = "";

    decodedstr[0] = input[0] << 2 | input[1] >> 4;
    decodedstr[1] = input[1] << 4 | input[2] >> 2;
    decodedstr[2] = input[2] << 6 | input[3] >> 0;
    strncat(output, decodedstr, oplen - strlen(output));

    return rc;
}