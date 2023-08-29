package com.example.adro.prefs

import androidx.datastore.core.Serializer
import com.example.domain.models.LoginResponse
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object UserPreferencesSerializer : Serializer<com.example.domain.models.LoginResponse.Data.User> {

    override val defaultValue: com.example.domain.models.LoginResponse.Data.User
        get() = com.example.domain.models.LoginResponse.Data.User()

    override suspend fun readFrom(input: InputStream): com.example.domain.models.LoginResponse.Data.User {
        return try {
            Json.decodeFromString(
                deserializer = com.example.domain.models.LoginResponse.Data.User.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: com.example.domain.models.LoginResponse.Data.User, output: OutputStream) {
        output.write(
            Json.encodeToString(serializer = com.example.domain.models.LoginResponse.Data.User.serializer(), t)
                .encodeToByteArray()
        )
    }

}