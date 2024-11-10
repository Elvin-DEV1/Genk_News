package com.example.genknews.control.model

data class VideoDetailResponse(
    val LastUpdated: LastUpdated,
    val OtherVideo: List<OtherVideo>,
    val VideoSameZone: List<Any>,
    val Videos: Videos
)

data class OtherVideo(
    val Author: String,
    val Avatar: String,
    val Capacity: Int,
    val CommentUrl: String,
    val Description: String,
    val DistributionDate: String,
    val Duration: String,
    val FileName: String,
    val HtmlCode: String,
    val Id: Int,
    val KeyVideo: String,
    val Name: String,
    val Pname: String,
    val Size: Size,
    val Source: String,
    val SourceLink: String,
    val Tags: String,
    val Url: String,
    val VideoRelation: String,
    val Views: Int,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneUrl: String
)

data class Videos(
    val Capacity: Int,
    val CommentCount: Int,
    val Id: Int,
    val ShareCount: Int,
    val SocialCount: Int,
    val Views: Int,
    val ZoneId: Int
)