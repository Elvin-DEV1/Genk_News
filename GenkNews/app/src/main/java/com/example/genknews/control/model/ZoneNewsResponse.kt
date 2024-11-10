package com.example.genknews.control.model

data class ZoneNewsResponse(
    val LastUpdated: LastUpdated,
    val News: List<NewZoneNewsResponse>
)

data class NewZoneNewsResponse(
    val Avatar: String,
    val DistributionDate: String,
    val IsPr: Boolean,
    val IsZoneNewsPosition: Int,
    val NewsId: String,
    val NewsRelation: List<Any>,
    val NewsType: Int,
    val Order: Int,
    val PrPosition: Int,
    val Sapo: String,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val TagSubTitleId: Int,
    val ThreadId: Int,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneShortURL: String
)