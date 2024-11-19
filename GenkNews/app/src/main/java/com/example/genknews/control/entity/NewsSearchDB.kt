package com.example.genknews.control.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "newsSearch")
data class NewsSearchDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "distribution_date")
    val distributionDate: String,

    @ColumnInfo(name = "news_id")
    val newsId: String,

    @ColumnInfo(name = "sapo")
    val sapo: String,

    @ColumnInfo(name = "tag_item")
    val tagItem: String,

    @ColumnInfo(name = "tags")
    val tags: List<String>,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "zone_id")
    val zoneId: Int,

    @ColumnInfo(name = "zone_name")
    val zoneName: String
) : Serializable