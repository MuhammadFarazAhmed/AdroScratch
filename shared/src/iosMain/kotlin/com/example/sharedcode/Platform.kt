package com.example.sharedcode

import com.example.sharedcode.home.presentation.HomeViewModel
import com.example.sharedcode.offers.presentation.OffersSharedViewModel
import io.ktor.client.engine.darwin.*
import kotlinx.cinterop.usePinned
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module
import platform.UIKit.UIDevice
import kotlin.native.internal.ExportForCppRuntime

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


actual fun getToken(): String = ""


actual fun platformModule() = module {
    single {
        Darwin.create()
    }

    single {
        HomeViewModel(get())
    }

    //single or factory can be used to get a view-model object for swiftui
}

actual interface CommonParcelable

object ViewModels : KoinComponent {
    fun getHomeViewModel() = get<HomeViewModel>()
    fun getOfferSharedViewModel() = get<OffersSharedViewModel>()
}

actual fun getOriginalResponse(): suspend (String) -> String? = { "" } //TODO get the decrypted response from CryptoKit Swift

actual class AesCipher actual constructor() {
    @ExportForCppRuntime
    actual fun encrypt(plainText: String, key: ByteArray, iv: ByteArray): ByteArray {
      return  memScoped {
            val plainData = NSData.dataWithBytes(plainText.refTo(0), plainText.size.convert())
            val keyData = NSData.dataWithBytes(key.refTo(0), key.size.convert())
            val ivData = NSData.dataWithBytes(iv.refTo(0), iv.size.convert())

            val encryptedData = AesCipherSwift().encrypt(plainText = plainData, key = keyData, iv = ivData)

             encryptedData?.let { encrypted ->
                ByteArray(encrypted.length).also { encrypted.toByteArray(it, encrypted.length) }
            }
        }
    }

    @ExportForCppRuntime
    actual fun decrypt(cipherText: ByteArray, key: ByteArray, iv: ByteArray): String {
       return memScoped {
            val cipherData = NSData.dataWithBytes(cipherText.refTo(0), cipherText.size.convert())
            val keyData = NSData.dataWithBytes(key.refTo(0), key.size.convert())
            val ivData = NSData.dataWithBytes(iv.refTo(0), iv.size.convert())

            val decryptedData = AesCipherSwift().decrypt(cipherText = cipherData, key = keyData, iv = ivData)

             decryptedData?.let { decrypted ->
                ByteArray(decrypted.length).also { decrypted.toByteArray(it, decrypted.length) }
            }
        }
    }

    private class AesCipherSwift {

        fun encrypt(plainText: NSData, key: NSData, iv: NSData): NSData? {
            return AesCipherImpl.encrypt(plainText, key, iv)
        }

        fun decrypt(cipherText: NSData, key: NSData, iv: NSData): NSData? {
            return AesCipherImpl.decrypt(cipherText, key, iv)
        }

        private object AesCipherImpl {

            fun encrypt(plainText: NSData, key: NSData, iv: NSData): NSData? {
                try {
                    val plainBuffer = plainText.bytes!!.readBytes(plainText.length.toInt())
                    val keyBuffer = key.bytes!!.readBytes(key.length.toInt())
                    val ivBuffer = iv.bytes!!.readBytes(iv.length.toInt())
                    val cipher = AesCipherImpl.createCipher(keyBuffer, ivBuffer, Cipher.ENCRYPT_MODE)
                    val encryptedBuffer = cipher.doFinal(plainBuffer)
                    return NSData.dataWithBytes(encryptedBuffer.refTo(0), encryptedBuffer.size.convert())
                } catch (e: Exception) {
                    e.printStackTrace()
                    return null
                }
            }

            fun decrypt(cipherText: NSData, key: NSData, iv: NSData): NSData? {
                try {
                    val cipherBuffer = cipherText.bytes!!.readBytes(cipherText.length.toInt())
                    val keyBuffer = key.bytes!!.readBytes(key.length.toInt())
                    val ivBuffer = iv.bytes!!.readBytes(iv.length.toInt())
                    val cipher = AesCipherImpl.createCipher(keyBuffer, ivBuffer, Cipher.DECRYPT_MODE)
                    val decryptedBuffer = cipher.doFinal(cipherBuffer)
}
