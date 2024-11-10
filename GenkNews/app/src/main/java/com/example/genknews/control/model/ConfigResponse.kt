package com.example.genknews.control.model

data class ConfigResponse(
    val magazineType: Int,
    val podcastType: Int,
    val showAudio: Boolean,
    val showComment: Boolean,
    val showCommentShareInList: Boolean,
    val supportedNativeNewsType: List<Int>
)