package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchResponse(
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

        @SerializedName("NewsId")
        @Expose
        val newsId: String,

        @SerializedName("Sapo")
        @Expose
        val sapo: String,

        @SerializedName("TagItem")
        @Expose
        val tagItem: String,

        @SerializedName("Tags")
        @Expose
        val tags: List<String>,

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
        val zoneName: String
    )
}