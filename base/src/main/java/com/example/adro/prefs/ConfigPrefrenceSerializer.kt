package com.example.adro.prefs

import androidx.datastore.core.Serializer
import com.example.adro.models.ConfigModel
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object ConfigPreferencesSerializer : Serializer<ConfigModel> {

    override val defaultValue: ConfigModel
        get() = ConfigModel()

    override suspend fun readFrom(input: InputStream): ConfigModel {
        return try {
            Json.decodeFromString(
                deserializer = ConfigModel.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: ConfigModel, output: OutputStream) {
        output.write(
            Json.encodeToString(serializer = ConfigModel.serializer(), t).encodeToByteArray()
        )
    }

}