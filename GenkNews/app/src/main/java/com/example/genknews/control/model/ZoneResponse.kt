package com.example.genknews.control.model

data class ZoneResponse(
    val LastUpdated: LastUpdated,
    val Zones: List<Zone>
)

data class Zone(
    val Domain: String,
    val Id: Int,
    val Name: String,
    val ShortURL: String
)