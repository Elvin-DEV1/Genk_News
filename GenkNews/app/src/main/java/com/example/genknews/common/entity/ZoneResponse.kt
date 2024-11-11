package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ZoneResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("Zones")
    @Expose
    val zones: List<Zone>
) {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class Zone(
        @SerializedName("Domain")
        @Expose
        val domain: String,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("Name")
        @Expose
        val name: String,

        @SerializedName("ShortURL")
        @Expose
        val shortURL: String
    )
}