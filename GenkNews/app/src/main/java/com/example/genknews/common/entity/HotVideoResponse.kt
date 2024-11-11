package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HotVideoResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,
    @SerializedName("Videos")
    @Expose
    val videos: List<Any>
) {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )
}