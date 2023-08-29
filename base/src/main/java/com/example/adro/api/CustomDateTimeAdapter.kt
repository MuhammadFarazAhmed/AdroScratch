package com.example.adro.api

import com.google.gson.*
import java.lang.reflect.Type
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object CustomDateTimeAdapter : JsonDeserializer<Date>, JsonSerializer<Date> {

    private val DATE_FORMATS = arrayOf("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd")
    private val dateFormatters: List<SimpleDateFormat> = DATE_FORMATS.map { SimpleDateFormat(it, Locale.US) }
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date? {
        for (formatter in dateFormatters) {
            try {
                return json?.asString?.let { formatter.parse(it) }
            } catch (ignore: ParseException) {
                throw JsonParseException("DateParseException: " + json.toString())
            }
        }
        throw JsonParseException("DateParseException: " + json.toString())
    }

    override fun serialize(src: Date?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        synchronized("designatedFormat") {
            val dateFormatAsString: String = "designatedFormat".format(src)
            return JsonPrimitive(dateFormatAsString)
        }
    }
}