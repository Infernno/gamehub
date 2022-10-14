package io.gamehub.core.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

// TODO: Make embedded entities
class ArrayStringConverter {
    @TypeConverter
    fun fromList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<List<String>>(value)
}
