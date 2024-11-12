package com.example.genknews.control.database

import androidx.room.TypeConverter
import com.example.genknews.control.entity.NewsDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromNewsRelationList(value: List<NewsDB.NewsRelation>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toNewsRelationList(value: String): List<NewsDB.NewsRelation> {
        val listType = object : TypeToken<List<NewsDB.NewsRelation>>() {}.type
        return Gson().fromJson(value, listType)
    }
}