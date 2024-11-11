package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoDetailResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("OtherVideo")
    @Expose
    val otherVideo: List<OtherVideo>,

    @SerializedName("VideoSameZone")
    @Expose
    val videoSameZone: List<Any>,

    @SerializedName("Videos")
    @Expose
    val videos: Videos
) {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class OtherVideo(
        @SerializedName("Author")
        @Expose
        val author: String,

        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("Capacity")
        @Expose
        val capacity: Int,

        @SerializedName("CommentUrl")
        @Expose
        val commentUrl: String,

        @SerializedName("Description")
        @Expose
        val description: String,

        @SerializedName("DistributionDate")
        @Expose
        val distributionDate: String,

        @SerializedName("Duration")
        @Expose
        val duration: String,

        @SerializedName("FileName")
        @Expose
        val fileName: String,

        @SerializedName("HtmlCode")
        @Expose
        val htmlCode: String,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("KeyVideo")
        @Expose
        val keyVideo: String,

        @SerializedName("Name")
        @Expose
        val name: String,

        @SerializedName("Pname")
        @Expose
        val pname: String,

        @SerializedName("Size")
        @Expose
        val size: Size,

        @SerializedName("Source")
        @Expose
        val source: String,

        @SerializedName("SourceLink")
        @Expose
        val sourceLink: String,

        @SerializedName("Tags")
        @Expose
        val tags: String,

        @SerializedName("Url")
        @Expose
        val url: String,

        @SerializedName("VideoRelation")
        @Expose
        val videoRelation: String,

        @SerializedName("Views")
        @Expose
        val views: Int,

        @SerializedName("ZoneId")
        @Expose
        val zoneId: Int,

        @SerializedName("ZoneName")
        @Expose
        val zoneName: String,

        @SerializedName("ZoneUrl")
        @Expose
        val zoneUrl: String
    ) {
        data class Size(
            @SerializedName("height")
            @Expose
            val height: Int,

            @SerializedName("width")
            @Expose
            val width: Int
        )
    }

    data class Videos(
        @SerializedName("Capacity")
        @Expose
        val capacity: Int,

        @SerializedName("CommentCount")
        @Expose
        val commentCount: Int,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("ShareCount")
        @Expose
        val shareCount: Int,

        @SerializedName("SocialCount")
        @Expose
        val socialCount: Int,

        @SerializedName("Views")
        @Expose
        val views: Int,

        @SerializedName("ZoneId")
        @Expose
        val zoneId: Int
    )
}