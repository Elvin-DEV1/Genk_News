package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ZoneNewsResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("News")
    @Expose
    val news: List<New>
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
        val newsRelation: List<Any>,

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
    )
}