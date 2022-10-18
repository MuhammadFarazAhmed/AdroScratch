package com.example.adro.security;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;

public class ApisEncryptionUtils {
    private CLibController controller;


    private static ApisEncryptionUtils encryptionUtils;
    private Cipher cipherInstance;
    private IvParameterSpec ivSalt;
    private SecretKeySpec aesSecretKeySpec;


    public ApisEncryptionUtils(CLibController controller) {
        this.controller = controller;
    }

    private Cipher getCipherInstance() {
        if (cipherInstance == null)
            try {
                cipherInstance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            } catch (NoSuchAlgorithmException ignore) {
            } catch (NoSuchPaddingException ignore) {
            }

        return cipherInstance;
    }

    private IvParameterSpec getIVSalt() {
        try {
            if (ivSalt == null) {
                //String value = getValue();
                ivSalt = new IvParameterSpec(controller.getAuSaltKey("").getBytes("UTF-8"));
            }
        } catch (UnsupportedEncodingException ignore) {
        }
        return ivSalt;
    }

    private SecretKeySpec getSecretKeySpec() {
        try {
            if (aesSecretKeySpec == null) {
                // String value = getValue();
                //ELog.INSTANCE.logDebug("getSecretKeySpec"+CLibController.Companion.getInstance().getAuSKey(""));
                //ELog.INSTANCE.logDebug("getIVSalt"+CLibController.Companion.getInstance().getAuSaltKey(""));
                aesSecretKeySpec = new SecretKeySpec(controller.getAuSKey("").getBytes("UTF-8"), "AES");
            }
        } catch (UnsupportedEncodingException ignore) {
        }
        return aesSecretKeySpec;
    }

    public synchronized String decryptString(String sirToDecrypt) {
        try {
            getCipherInstance().init(Cipher.DECRYPT_MODE, getSecretKeySpec(),getIVSalt());
            byte[] cipherText = Base64.decode(sirToDecrypt, Base64.DEFAULT);
            byte[] original = getCipherInstance().doFinal(cipherText);

            return new String(original, "UTF-8");

        } catch (Exception ignore) {
        }

        return null;
    }

    public synchronized String encryptString(String sirToEncrypt) {
        try {
            getCipherInstance().init(Cipher.ENCRYPT_MODE, getSecretKeySpec(), getIVSalt());
            byte[] cipherText = sirToEncrypt.getBytes("UTF-8");
            byte[] encryptedBytes = getCipherInstance().doFinal(cipherText);

            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (Exception ignore) {
        }

        return null;
    }


    /*private String getValue() {
        return XOREncryption.decryptFromCKey(CLibController.Companion.getInstance().getDE(generateRandomString()));
    }*/
    private String generateRandomString() {

        StringBuilder str = new StringBuilder();
        char[] a = {'@', 'e', 'l', 'l', '%', 'l', 'd', 'h', 'o', '&', 'w', 'o', '%', 'l', 'd', 'h', 'e', 'l', 'U', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        for (int n = 2; n < 40; n++) {
            str.append(a[n / 2]);
        }
        return str.toString();
    }

}
