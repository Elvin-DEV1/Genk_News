package com.example.genknews.control.model

data class NewsResponse(
    val LastUpdated: LastUpdated,
    val News: List<NewsNewsResponse>
)

data class NewsNewsResponse(
    val Avatar: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val InitSapo: String,
    val NewsId: String,
    val NewsRelation: List<NewsRelation>,
    val NewsType: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneShortURL: String
)

data class NewsRelation(
    val Author: String,
    val Avatar: String,
    val Avatar1: String,
    val Avatar2: String,
    val Avatar3: String,
    val Avatar4: String,
    val Avatar5: String,
    val AvatarPreload: String,
    val DisplayStyle: Int,
    val DistributionDate: String,
    val NewsId: Long,
    val NewsRelationType: Int,
    val ObjectType: Int,
    val Sapo: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)