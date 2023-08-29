package com.example.adro.prefs

import androidx.datastore.core.Serializer
import com.example.domain.models.ConfigModel
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object ConfigPreferencesSerializer : Serializer<com.example.domain.models.ConfigModel> {

    override val defaultValue: com.example.domain.models.ConfigModel
        get() = com.example.domain.models.ConfigModel()

    override suspend fun readFrom(input: InputStream): com.example.domain.models.ConfigModel {
        return try {
            Json.decodeFromString(
                deserializer = com.example.domain.models.ConfigModel.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: com.example.domain.models.ConfigModel, output: OutputStream) {
        output.write(
            Json.encodeToString(serializer = com.example.domain.models.ConfigModel.serializer(), t).encodeToByteArray()
        )
    }

}