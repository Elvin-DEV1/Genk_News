package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConfigResponse(
    @SerializedName("MagazineType")
    @Expose
    val magazineType: Int,
    @SerializedName("PodcastType")
    @Expose
    val podcastType: Int,
    @SerializedName("ShowAudio")
    @Expose
    val showAudio: Boolean,
    @SerializedName("ShowComment")
    @Expose
    val showComment: Boolean,
    @SerializedName("ShowCommentShareInList")
    @Expose
    val showCommentShareInList: Boolean,
    @SerializedName("SupportedNativeNewsType")
    @Expose
    val supportedNativeNewsType: List<Int>
)