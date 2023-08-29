package com.example.adro.security

import android.text.TextUtils
import android.util.Base64
import com.theentertainerme.adro.security.CLibController
import java.io.UnsupportedEncodingException
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EncryptionUtils @Inject constructor(private val controller: CLibController) {

    private var cipherInstance: Cipher? = null
    private var ivSalt: IvParameterSpec? = null
    private var aesSecretKeySpec: SecretKeySpec? = null
    private var secretKey: String = ""
    private var ivSaltKey: String = ""

    init {
        try {
            secretKey = controller.getAuSKey("")
            ivSaltKey = controller.getAuSaltKey("")
            cipherInstance = Cipher.getInstance("AES/CBC/PKCS7Padding")
        } catch (ignore: NoSuchAlgorithmException) {
        } catch (ignore: NoSuchPaddingException) {
        }
    }

    private fun getIVSalt(): IvParameterSpec {
        try {
            if (ivSalt == null) {
                if (TextUtils.isEmpty(ivSaltKey)) {
                    ivSaltKey = controller.getAuSaltKey()
                }
                ivSalt = IvParameterSpec(
                    ivSaltKey.toByteArray(charset("UTF-8"))
                )
            }
        } catch (ignore: UnsupportedEncodingException) {
        }

        return ivSalt!!
    }

    private fun getSecretKeySpec(): SecretKeySpec {
        try {
            if (aesSecretKeySpec == null) {
                if (TextUtils.isEmpty(secretKey)) {
                    secretKey = controller.getAuSKey("")
                }
                aesSecretKeySpec = SecretKeySpec(
                    secretKey.toByteArray(charset("UTF-8")), "AES"
                )
            }
        } catch (ignore: UnsupportedEncodingException) {
        }

        return aesSecretKeySpec!!
    }


    fun decryptString(sirToDecrypt: String): String? {
        try {
            cipherInstance?.init(
                Cipher.DECRYPT_MODE,
                getSecretKeySpec(),
                getIVSalt()
            )
            val cipherText = Base64.decode(sirToDecrypt.trim(), Base64.DEFAULT)
            val original = cipherInstance?.doFinal(cipherText)
            return String(original!!, charset("UTF-8"))

        } catch (ignore: Exception) {
        }


        return null
    }

    fun encryptString(sirToEncrypt: String): String? {
        try {
            cipherInstance?.init(
                Cipher.ENCRYPT_MODE,
                getSecretKeySpec(),
                getIVSalt()
            )
            val cipherText = sirToEncrypt.toByteArray(charset("UTF-8"))
            val encryptedBytes = cipherInstance?.doFinal(cipherText)
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT).trim()
        } catch (ignore: Exception) {
        }
        return null
    }

}