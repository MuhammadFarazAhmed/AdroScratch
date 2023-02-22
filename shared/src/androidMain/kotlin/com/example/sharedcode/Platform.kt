package com.example.sharedcode

import android.os.Parcelable
import android.util.Base64
import io.jsonwebtoken.*
import io.ktor.client.engine.android.*
import kotlinx.parcelize.Parcelize
import org.koin.dsl.module
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getToken(): String = Jwts.builder().setHeaderParam(JwsHeader.TYPE, JwsHeader.JWT_TYPE)
    .claim("company", "ADO")
    .claim("session_token", "")
    .claim("api_token", "k229rn-j#5W9-J8D#6-A6M0(o-!7#9&4\$x").signWith(
        SignatureAlgorithm.HS256,
        Base64.encodeToString(
            "!EyFde4#\$%gYsRct54fy@#\$5".toByteArray(),
            Base64.DEFAULT
        )
    ).compact()


actual fun platformModule() = module {
    single {
        Android.create()
    }

    single {
        getToken()
    }

    single { CLibController }

//    single { ApisEncryptionUtils.getInstance(CLibController) }
}


actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable

actual fun getOriginalResponse(): suspend (response: String) -> String? =
    { it -> ApisEncryptionUtils.getInstance(CLibController).decryptString(it) }

actual class AesCipher actual constructor() {
    actual fun encrypt(plainText: String, key: ByteArray, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        val secretKey = SecretKeySpec(key, "AES")
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
        return cipher.doFinal(plainText.toByteArray())
    }

    actual fun decrypt(cipherText: ByteArray, key: ByteArray, iv: ByteArray): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        val secretKey = SecretKeySpec(key, "AES")
        val ivParameterSpec = IvParameterSpec(iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec)
        return cipher.doFinal(Base64.decode(cipherText,Base64.DEFAULT)).toString(Charsets.UTF_8)
    }
}
