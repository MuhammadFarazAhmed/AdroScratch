package com.example.adro.prefs

import androidx.datastore.core.Serializer
import com.example.domain.models.ConfigModel
import com.example.domain.models.LoginResponse
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object UserPreferencesSerializer : Serializer<LoginResponse.Data.User> {

    override val defaultValue: LoginResponse.Data.User
        get() = LoginResponse.Data.User()

    override suspend fun readFrom(input: InputStream): LoginResponse.Data.User {
        return try {
            Json.decodeFromString(
                deserializer = LoginResponse.Data.User.serializer(),
                input.readBytes().decodeToString()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: LoginResponse.Data.User, output: OutputStream) {
        output.write(
            Json.encodeToString(serializer = LoginResponse.Data.User.serializer(), t)
                .encodeToByteArray()
        )
    }

}