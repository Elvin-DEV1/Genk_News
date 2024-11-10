package com.example.genknews.control.model

data class TagResponse(
    val DetailNewsPosition: DetailNewsPosition,
    val LastUpdated: LastUpdated,
    val News: News
)

data class DetailNewsPosition(
    val Data: List<DataDetailNewsPosition>
)

data class DataDetailNewsPosition(
    val Avatar: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val NewsId: String,
    val NewsRelation: List<NewsRelation>,
    val NewsType: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val ThreadName: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String
)

data class News(
    val Data: List<Any>,
    val TagsInfo: TagsInfo
)

data class TagsInfo(
    val Id: Int
)