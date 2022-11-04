import com.example.sharedcode.security.CLibController


import io.ktor.utils.io.core.*
import kotlin.String
import javax.crypto.Cipher

import javax.crypto.NoSuchPaddingException

import javax.crypto.spec.IvParameterSpec

import javax.crypto.spec.SecretKeySpec

import kotlin.jvm.Synchronized

class ApisEncryptionUtils(private val controller: CLibController) {

    private var cipherInstance: Cipher? = null
        get() {
            if (field == null) try {
                field = Cipher.getInstance("AES/CBC/PKCS7Padding")
            } catch (ignore: java.security.NoSuchAlgorithmException) {
            } catch (ignore: NoSuchPaddingException) {
            }
            return field
        }
    private var ivSalt: spec.IvParameterSpec? = null
    private var aesSecretKeySpec: spec.SecretKeySpec? = null

    //String value = getValue();
    private val iVSalt: spec.IvParameterSpec?
        private get() {
            try {
                if (ivSalt == null) {
                    //String value = getValue();
                    ivSalt = spec.IvParameterSpec(
                        controller.getAuSaltKey("").getBytes("UTF-8")
                    )
                }
            } catch (ignore: java.io.UnsupportedEncodingException) {
            }
            return ivSalt
        }

    // String value = getValue();
    //ELog.INSTANCE.logDebug("getSecretKeySpec"+CLibController.Companion.getInstance().getAuSKey(""));
    //ELog.INSTANCE.logDebug("getIVSalt"+CLibController.Companion.getInstance().getAuSaltKey(""));
    private val secretKeySpec:SecretKeySpec?
        get() {
            try {
                if (aesSecretKeySpec == null) {
                    // String value = getValue();
                    //ELog.INSTANCE.logDebug("getSecretKeySpec"+CLibController.Companion.getInstance().getAuSKey(""));
                    //ELog.INSTANCE.logDebug("getIVSalt"+CLibController.Companion.getInstance().getAuSaltKey(""));
                    aesSecretKeySpec = spec.SecretKeySpec(
                        controller.getAuSKey("").getBytes("UTF-8"),
                        "AES"
                    )
                }
            } catch (ignore:UnsupportedEncodingException) {
            }
            return aesSecretKeySpec
        }

    @Synchronized
    fun decryptString(sirToDecrypt: String): String {
        try {
            cipherInstance.init(Cipher.DECRYPT_MODE, secretKeySpec, iVSalt)
            val cipherText: ByteArray =
                Base64.decode(sirToDecrypt, Base64.DEFAULT)
            val original: ByteArray = cipherInstance.doFinal(cipherText)
            return String(original, "UTF-8")
        } catch (ignore: Exception) {
        }
        return ""
    }

    @Synchronized
    fun encryptString(sirToEncrypt: String): String? {
        try {
            cipherInstance.init(Cipher.ENCRYPT_MODE, secretKeySpec, iVSalt)
            val cipherText: ByteArray = sirToEncrypt.toByteArray(charset = "UTF-8")
            val encryptedBytes: ByteArray = cipherInstance.doFinal(cipherText)
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        } catch (ignore: Exception) {
        }
        return null
    }

    /*private String getValue() {
        return XOREncryption.decryptFromCKey(CLibController.Companion.getInstance().getDE(generateRandomString()));
    }*/
    private fun generateRandomString(): String {
        val str = StringBuilder()
        val a = charArrayOf(
            '@',
            'e',
            'l',
            'l',
            '%',
            'l',
            'd',
            'h',
            'o',
            '&',
            'w',
            'o',
            '%',
            'l',
            'd',
            'h',
            'e',
            'l',
            'U',
            'o',
            ' ',
            'w',
            'o',
            'r',
            'l',
            'd'
        )
        for (n in 2..39) {
            str.append(a[n / 2])
        }
        return str.toString()
    }

    companion object {
        private val encryptionUtils: ApisEncryptionUtils? = null
    }
}