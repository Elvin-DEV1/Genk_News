package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MenuResponse(
    @SerializedName("categoriesBox")
    @Expose
    val categoriesBox: MutableList<CategoriesBox>
) {
    data class CategoriesBox(
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
    )
}