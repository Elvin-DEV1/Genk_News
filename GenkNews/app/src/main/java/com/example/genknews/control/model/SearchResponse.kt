package com.example.genknews.control.model

data class SearchResponse(
    val LastUpdated: LastUpdated,
    val News: List<New>
)

data class New(
    val Avatar: String,
    val DistributionDate: String,
    val NewsId: String,
    val Sapo: String,
    val TagItem: String,
    val Tags: List<String>,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String
)