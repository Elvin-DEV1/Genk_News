package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoZoneResponse(
    @SerializedName("Data")
    @Expose
    val data: List<Data>,

    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated
) {
    data class Data(
        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("AvatarCover")
        @Expose
        val avatarCover: String,

        @SerializedName("CountVideo")
        @Expose
        val countVideo: Int,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("Name")
        @Expose
        val name: String,

        @SerializedName("Order")
        @Expose
        val order: Int,

        @SerializedName("ParentId")
        @Expose
        val parentId: Int,

        @SerializedName("Status")
        @Expose
        val status: Int,

        @SerializedName("Url")
        @Expose
        val url: String
    )

    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )
}