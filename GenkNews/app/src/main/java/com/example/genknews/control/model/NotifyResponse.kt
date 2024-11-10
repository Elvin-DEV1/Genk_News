package com.example.genknews.control.model

data class NotifyResponse(
    val LastUpdated: LastUpdated,
    val Notify: List<Notify>,
    val NotifyTopView: List<NotifyTopView>
)

data class Notify(
    val Author: String,
    val Avatar: String,
    val Avatar5: String,
    val ChannelId: Int,
    val CommentCount: Int,
    val Id: Int,
    val IsProcess: Boolean,
    val NewsId: Long,
    val PublishDate: String,
    val PushDate: String,
    val Sapo: String,
    val ShareCount: Int,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneShortURL: String
)

data class NotifyTopView(
    val Author: String,
    val Avatar: String,
    val DistributionDate: String,
    val InitSapo: String,
    val NewsId: String,
    val NewsType: Int,
    val Sapo: String,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val Title: String,
    val Type: Int,
    val Url: String,
    val UrlShare: String,
    val ZoneId: Int
)