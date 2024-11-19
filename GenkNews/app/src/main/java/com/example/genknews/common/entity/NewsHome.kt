package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsHome(
    @SerializedName("Avatar")
    @Expose
    val avatar: String,

    @SerializedName("CommentCount")
    @Expose
    val commentCount: Int,

    @SerializedName("DistributionDate")
    @Expose
    val distributionDate: String,

    @SerializedName("NewsId")
    @Expose
    val newsId: String,

    @SerializedName("NewsRelation")
    @Expose
    val newsHomeRelation: List<NewsHomeRelation>,

    @SerializedName("NewsType")
    @Expose
    val newsType: Int,

    @SerializedName("Order")
    @Expose
    val order: Int,

    @SerializedName("Sapo")
    @Expose
    val sapo: String,

    @SerializedName("ShareCount")
    @Expose
    val shareCount: Int,

    @SerializedName("Source")
    @Expose
    val source: String,

    @SerializedName("SourceLink")
    @Expose
    val sourceLink: String,

    @SerializedName("SubTitle")
    @Expose
    val subTitle: String,

    @SerializedName("ThreadId")
    @Expose
    val threadId: Int,

    @SerializedName("ThreadName")
    @Expose
    val threadName: String,

    @SerializedName("Title")
    @Expose
    val title: String,

    @SerializedName("Type")
    @Expose
    val type: Int,

    @SerializedName("Url")
    @Expose
    val url: String,

    @SerializedName("ZoneId")
    @Expose
    val zoneId: Int,

    @SerializedName("ZoneName")
    @Expose
    val zoneName: String,

    @SerializedName("ZoneShortURL")
    @Expose
    val zoneShortURL: String
) : Serializable
