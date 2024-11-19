package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class NewsLatestRelation(
    @SerializedName("Author")
    @Expose
    val author: String,

    @SerializedName("Avatar")
    @Expose
    val avatar: String,

    @SerializedName("Avatar1")
    @Expose
    val avatar1: String,

    @SerializedName("Avatar2")
    @Expose
    val avatar2: String,

    @SerializedName("Avatar3")
    @Expose
    val avatar3: String,

    @SerializedName("Avatar4")
    @Expose
    val avatar4: String,

    @SerializedName("Avatar5")
    @Expose
    val avatar5: String,

    @SerializedName("AvatarPreload")
    @Expose
    val avatarPreload: String,

    @SerializedName("DisplayStyle")
    @Expose
    val displayStyle: Int,

    @SerializedName("DistributionDate")
    @Expose
    val distributionDate: String,

    @SerializedName("NewsId")
    @Expose
    val newsId: Long,

    @SerializedName("NewsRelationType")
    @Expose
    val newsRelationType: Int,

    @SerializedName("ObjectType")
    @Expose
    val objectType: Int,

    @SerializedName("Sapo")
    @Expose
    val sapo: String,

    @SerializedName("Title")
    @Expose
    val title: String,

    @SerializedName("Type")
    @Expose
    val type: Int,

    @SerializedName("Url")
    @Expose
    val url: String,

    @SerializedName("ZoneId")
    @Expose
    val zoneId: Int
) : Serializable
