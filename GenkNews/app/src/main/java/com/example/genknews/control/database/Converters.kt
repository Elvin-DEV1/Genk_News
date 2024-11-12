package com.example.genknews.control.database

import androidx.room.TypeConverter
import com.example.genknews.control.entity.NewsLatestDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromNewsRelationList(value: List<NewsLatestDB.NewsRelation>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toNewsRelationList(value: String): List<NewsLatestDB.NewsRelation> {
        val listType = object : TypeToken<List<NewsLatestDB.NewsRelation>>() {}.type
        return Gson().fromJson(value, listType)
    }
}