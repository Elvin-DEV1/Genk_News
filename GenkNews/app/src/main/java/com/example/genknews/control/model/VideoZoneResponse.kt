package com.example.genknews.control.model

data class VideoZoneResponse(
    val Data: List<DataVideoZoneResponse>,
    val LastUpdated: LastUpdated
)

data class DataVideoZoneResponse(
    val Avatar: String,
    val AvatarCover: String,
    val CountVideo: Int,
    val Id: Int,
    val Name: String,
    val Order: Int,
    val ParentId: Int,
    val Status: Int,
    val Url: String
)