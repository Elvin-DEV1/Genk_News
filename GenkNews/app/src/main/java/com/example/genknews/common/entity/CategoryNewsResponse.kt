package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryNewsResponse(
    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("News")
    @Expose
    val news: MutableList<NewsCategory>
) : Serializable {
    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )
}