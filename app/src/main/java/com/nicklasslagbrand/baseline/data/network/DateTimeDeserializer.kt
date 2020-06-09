package com.nicklasslagbrand.baseline.data.network

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import org.joda.time.DateTime

class DateTimeDeserializer : JsonDeserializer<DateTime> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): DateTime {
        if (json == null) {
            throw IllegalArgumentException("Received null time object.")
        }

        val time = json.asString

        return DateTime.parse(time)
    }
}
