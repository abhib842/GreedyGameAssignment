package com.example.fluperdemotask.data.database.converter

import androidx.room.TypeConverter
import com.example.fluperdemotask.data.database.entity.Stores
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class StoresConverters {
    @TypeConverter
    fun fromString(value: String): List<Stores> {
        val listType = object : TypeToken<List<Stores>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromString(value: List<Stores>): String {
        val gson = Gson()
        return gson.toJson(value)
    }
}