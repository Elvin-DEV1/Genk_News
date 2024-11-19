package com.example.genknews.control.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable

@Entity(tableName = "news")
data class NewsLatestDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "comment_count")
    val commentCount: Int,

    @ColumnInfo(name = "distribution_date")
    val distributionDate: String,

    @ColumnInfo(name = "news_id")
    val newsId: Int = 0,

    @ColumnInfo(name = "init_sapo")
    val initSapo: String,

    @ColumnInfo(name = "news_relation")
    val newsRelation: List<NewsRelation>,

    @ColumnInfo(name = "news_type")
    val newsType: Int,

    @ColumnInfo(name = "sapo")
    val sapo: String,

    @ColumnInfo(name = "share_count")
    val shareCount: Int,

    @ColumnInfo(name = "source")
    val source: String,

    @ColumnInfo(name = "source_link")
    val sourceLink: String,

    @ColumnInfo(name = "sub_title")
    val subTitle: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "type")
    val type: Int,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "zone_id")
    val zoneId: Int,

    @ColumnInfo(name = "zone_name")
    val zoneName: String,

    @ColumnInfo(name = "zone_short_url")
    val zoneShortURL: String
) : Serializable