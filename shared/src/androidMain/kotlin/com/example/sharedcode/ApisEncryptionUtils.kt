package com.example.sharedcode;

import android.util.Base64;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.Synchronized;

class ApisEncryptionUtils private constructor(private var controller: CLibController) {


    private var cipherInstance: Cipher? = null
    private var ivSalt: IvParameterSpec? = null
    private var aesSecretKeySpec: SecretKeySpec? = null

    companion object {
        private var encryptionUtils: ApisEncryptionUtils? = null
        fun getInstance(controller: CLibController): ApisEncryptionUtils {
            if (encryptionUtils == null) {
                encryptionUtils = ApisEncryptionUtils(controller)
            }
            return encryptionUtils as ApisEncryptionUtils
        }
    }


    private fun getCipherInstance(): Cipher? {
        if (cipherInstance == null) try {
            cipherInstance = Cipher.getInstance("AES/CBC/PKCS7Padding")
        } catch (ignore: NoSuchAlgorithmException) {
        } catch (ignore: NoSuchPaddingException) {
        }
        return cipherInstance
    }

    private fun getIVSalt(): IvParameterSpec? {
        try {
            if (ivSalt == null) {
                ivSalt =
                    IvParameterSpec(controller.getAuSaltKey().toByteArray(Charset.forName("UTF-8")))
            }
        } catch (ignore: UnsupportedEncodingException) {
        }
        return ivSalt
    }

    private fun getSecretKeySpec(): SecretKeySpec? {
        try {
            if (aesSecretKeySpec == null) {
                aesSecretKeySpec =
                    SecretKeySpec(
                        controller.getAuSKey().toByteArray(Charset.forName("UTF-8")),
                        "AES"
                    )
            }
        } catch (ignore: UnsupportedEncodingException) {
        }
        return aesSecretKeySpec
    }

    @Synchronized
    fun decryptString(sirToDecrypt: String?): String? {
        return try {
            getInstance(controller).getCipherInstance()?.init(
                Cipher.DECRYPT_MODE,
                getInstance(controller).getSecretKeySpec(),
                getInstance(controller).getIVSalt()
            )
            val cipherText: ByteArray = Base64.decode(sirToDecrypt, Base64.DEFAULT)
            String(getInstance(controller).getCipherInstance()!!.doFinal(Base64.decode(cipherText,Base64.DEFAULT)), Charset.forName("UTF-8"))
        } catch (ignore: Exception) {
            Log.d("TAG", "decryptString: $ignore")
            null
        }
    }

    @Synchronized
    fun encryptString(sirToEncrypt: String): String? {
        try {
            getInstance(controller).getCipherInstance()?.init(
                Cipher.ENCRYPT_MODE,
                getInstance(controller).getSecretKeySpec(),
                getInstance(controller).getIVSalt()
            )
            val cipherText = sirToEncrypt.toByteArray(charset("UTF-8"))
            val encryptedBytes = getInstance(controller).getCipherInstance()!!.doFinal(cipherText)
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT)
        } catch (ignore: Exception) {
        }
        return null
    }


    /*private String getValue() {
        return XOREncryption.decryptFromCKey(CLibController.Companion.getInstance().getDE(generateRandomString()));
    }*/
    private fun generateRandomString(): String? {
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
    }

//public class ApisEncryptionUtils
//{
//
//    private static ApisEncryptionUtils encryptionUtils;
//    private Cipher cipherInstance;
//    private IvParameterSpec ivSalt;
//    private SecretKeySpec aesSecretKeySpec;
//
//    private String secretKey = "";
//    private String salt = "";
//
//    @JvmStatic
//    public static ApisEncryptionUtils getInstance(@NotNull String secretKey, @NotNull String salt) {
//        if (encryptionUtils == null)
//            encryptionUtils = new ApisEncryptionUtils(secretKey, salt);
//        return encryptionUtils;
//    }
//
//    private ApisEncryptionUtils(@NotNull String secretKet, @NotNull String salt) {
//        this.secretKey = secretKet;
//        this.salt = salt;
//    }
//
//    private Cipher getCipherInstance() {
//        if (cipherInstance == null)
//            try {
//                cipherInstance = Cipher.getInstance("AES/CBC/PKCS7Padding");
//            } catch (NoSuchAlgorithmException ignore) {
//            } catch (NoSuchPaddingException ignore) {
//            }
//
//        return cipherInstance;
//    }
//
//    private IvParameterSpec getIVSalt() {
//        try {
//            if (ivSalt == null) {
//                //String value = getValue();
//                ivSalt = new IvParameterSpec(salt.getBytes("UTF-8"));
//            }
//        } catch (UnsupportedEncodingException ignore) {
//        }
//        return ivSalt;
//    }
//
//    private SecretKeySpec getSecretKeySpec() {
//        try {
//            if (aesSecretKeySpec == null) {
//                // String value = getValue();
//                //ELog.INSTANCE.logDebug("getSecretKeySpec"+CLibController.Companion.getInstance().getAuSKey(""));
//                //ELog.INSTANCE.logDebug("getIVSalt"+CLibController.Companion.getInstance().getAuSaltKey(""));
//                aesSecretKeySpec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");
//            }
//        } catch (UnsupportedEncodingException ignore) {
//        }
//        return aesSecretKeySpec;
//    }
//
//    public synchronized String decryptString(String sirToDecrypt) {
//        try {
//            getCipherInstance().init(Cipher.DECRYPT_MODE, getSecretKeySpec(), getIVSalt());
//            byte[] cipherText = Base64.decode(sirToDecrypt, Base64.DEFAULT);
//            byte[] original = getCipherInstance().doFinal(cipherText);
//
//            return new String(original, "UTF-8");
//
//        } catch (Exception ignore) {
//        }
//
//        return null;
//    }
//
//    public synchronized String encryptString(String sirToEncrypt) {
//        try {
//            getCipherInstance().init(Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIVSalt());
//            byte[] cipherText = sirToEncrypt.getBytes("UTF-8");
//            byte[] encryptedBytes = getCipherInstance().doFinal(cipherText);
//
//            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
//        } catch (Exception ignore) {
//        }
//
//        return null;
//    }
//
//
//    /*private String getValue() {
//        return XOREncryption.decryptFromCKey(CLibController.Companion.getInstance().getDE(generateRandomString()));
//    }*/
//    private String generateRandomString() {
//
//        StringBuilder str = new StringBuilder();
//        char[] a = {'@', 'e', 'l', 'l', '%', 'l', 'd', 'h', 'o', '&', 'w', 'o', '%', 'l', 'd', 'h', 'e', 'l', 'U', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
//        for (int n = 2; n < 40; n++) {
//            str.append(a[n / 2]);
//        }
//        return str.toString();
//    }
//}

//}