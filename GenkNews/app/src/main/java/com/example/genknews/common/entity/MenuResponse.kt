package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MenuResponse(
    @SerializedName("categoriesBox")
    @Expose
    val categoriesBox: MutableList<Category>
)