package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotifyResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("Notify")
    @Expose
    val notify: List<NotifyItem>,

    @SerializedName("NotifyTopView")
    @Expose
    val notifyTopView: List<NotifyTopView>
) {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class NotifyItem(
        @SerializedName("Author")
        @Expose
        val author: String,

        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("Avatar5")
        @Expose
        val avatar5: String,

        @SerializedName("ChannelId")
        @Expose
        val channelId: Int,

        @SerializedName("CommentCount")
        @Expose
        val commentCount: Int,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("IsProcess")
        @Expose
        val isProcess: Boolean,

        @SerializedName("NewsId")
        @Expose
        val newsId: Long,

        @SerializedName("PublishDate")
        @Expose
        val publishDate: String,

        @SerializedName("PushDate")
        @Expose
        val pushDate: String,

        @SerializedName("Sapo")
        @Expose
        val sapo: String,

        @SerializedName("ShareCount")
        @Expose
        val shareCount: Int,

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
    )

    data class NotifyTopView(
        @SerializedName("Author")
        @Expose
        val author: String,

        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("DistributionDate")
        @Expose
        val distributionDate: String,

        @SerializedName("InitSapo")
        @Expose
        val initSapo: String,

        @SerializedName("NewsId")
        @Expose
        val newsId: String,

        @SerializedName("NewsType")
        @Expose
        val newsType: Int,

        @SerializedName("Sapo")
        @Expose
        val sapo: String,

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

        @SerializedName("Title")
        @Expose
        val title: String,

        @SerializedName("Type")
        @Expose
        val type: Int,

        @SerializedName("Url")
        @Expose
        val url: String,

        @SerializedName("UrlShare")
        @Expose
        val urlShare: String,

        @SerializedName("ZoneId")
        @Expose
        val zoneId: Int
    )
}