package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ZoneNewsResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("News")
    @Expose
    val news: MutableList<New>
) {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class New(
        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("DistributionDate")
        @Expose
        val distributionDate: String,

        @SerializedName("IsPr")
        @Expose
        val isPr: Boolean,

        @SerializedName("IsZoneNewsPosition")
        @Expose
        val isZoneNewsPosition: Int,

        @SerializedName("NewsId")
        @Expose
        val newsId: String,

        @SerializedName("NewsRelation")
        @Expose
        val newsRelation: List<NewsRelation>,

        @SerializedName("NewsType")
        @Expose
        val newsType: Int,

        @SerializedName("Order")
        @Expose
        val order: Int,

        @SerializedName("PrPosition")
        @Expose
        val prPosition: Int,

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

        @SerializedName("TagSubTitleId")
        @Expose
        val tagSubTitleId: Int,

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

        @SerializedName("ZoneId")
        @Expose
        val zoneId: Int,

        @SerializedName("ZoneName")
        @Expose
        val zoneName: String,

        @SerializedName("ZoneShortURL")
        @Expose
        val zoneShortURL: String
    ){
        data class NewsRelation(
            @SerializedName("Author")
            @Expose
            val author: String,

            @SerializedName("Avatar")
            @Expose
            val avatar: String,

            @SerializedName("Avatar1")
            @Expose
            val avatar1: String,

            @SerializedName("Avatar2")
            @Expose
            val avatar2: String,

            @SerializedName("Avatar3")
            @Expose
            val avatar3: String,

            @SerializedName("Avatar4")
            @Expose
            val avatar4: String,

            @SerializedName("Avatar5")
            @Expose
            val avatar5: String,

            @SerializedName("AvatarPreload")
            @Expose
            val avatarPreload: String,

            @SerializedName("DisplayStyle")
            @Expose
            val displayStyle: Int,

            @SerializedName("DistributionDate")
            @Expose
            val distributionDate: String,

            @SerializedName("NewsId")
            @Expose
            val newsId: Long,

            @SerializedName("NewsRelationType")
            @Expose
            val newsRelationType: Int,

            @SerializedName("ObjectType")
            @Expose
            val objectType: Int,

            @SerializedName("Sapo")
            @Expose
            val sapo: String,

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
            val zoneId: Int
        )
    }
}