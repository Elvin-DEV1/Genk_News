package com.example.genknews.control.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.genknews.control.database.Converters

@Entity(tableName = "news")
data class NewsLatestDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "news_id")
    val newsId: Int = 0,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "comment_count")
    val commentCount: Int,

    @ColumnInfo(name = "distribution_date")
    val distributionDate: String,

    @ColumnInfo(name = "init_sapo")
    val initSapo: String,

    @TypeConverters(Converters::class)
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
) {
    data class NewsRelation(
        val author: String,
        val avatar: String,
        val avatar1: String,
        val avatar2: String,
        val avatar3: String,
        val avatar4: String,
        val avatar5: String,
        val avatarPreload: String,
        val displayStyle: Int,
        val distributionDate: String,
        val newsId: Long,
        val newsRelationType: Int,
        val objectType: Int,
        val sapo: String,
        val title: String,
        val type: Int,
        val url: String,
        val zoneId: Int
    )
}