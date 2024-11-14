package com.example.genknews.control.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.genknews.control.database.relation.Converters

@Entity(tableName = "newsZone")
data class NewsCategoryDB(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "avatar")
    val avatar: String,

    @ColumnInfo(name = "distribution_date")
    val distributionDate: String,

    @ColumnInfo(name = "is_pr")
    val isPr: Boolean,

    @ColumnInfo(name = "is_zone_news_position")
    val isZoneNewsPosition: Int,

    @ColumnInfo(name = "news_id")
    val newsId: String,

    @TypeConverters(Converters::class)
    @ColumnInfo(name = "news_relation")
    val newsRelation: List<NewsRelation>,

    @ColumnInfo(name = "news_type")
    val newsType: Int,

    @ColumnInfo(name = "order")
    val order: Int,

    @ColumnInfo(name = "pr_position")
    val prPosition: Int,

    @ColumnInfo(name = "sapo")
    val sapo: String,

    @ColumnInfo(name = "source")
    val source: String,

    @ColumnInfo(name = "source_link")
    val sourceLink: String,

    @ColumnInfo(name = "sub_title")
    val subTitle: String,

    @ColumnInfo(name = "tag_sub_title")
    val tagSubTitleId: Int,

    @ColumnInfo(name = "thread_id")
    val threadId: Int,

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
)