package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("Domain")
    @Expose
    val domain: String,
    @SerializedName("Id")
    @Expose
    val id: Int,
    @SerializedName("Logo")
    @Expose
    val logo: String,
    @SerializedName("Name")
    @Expose
    val name: String,
    @SerializedName("ShortURL")
    @Expose
    val shortURL: String
) : Serializable
