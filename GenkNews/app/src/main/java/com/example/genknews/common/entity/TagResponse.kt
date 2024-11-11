package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TagResponse(
    @SerializedName("DetailNewsPosition")
    @Expose
    val detailNewsPosition: DetailNewsPosition,

    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("News")
    @Expose
    val news: News
) {
    data class DetailNewsPosition(
        @SerializedName("Data")
        @Expose
        val data: List<Data>
    ) {
        data class Data(
            @SerializedName("Avatar")
            @Expose
            val avatar: String,

            @SerializedName("CommentCount")
            @Expose
            val commentCount: Int,

            @SerializedName("DistributionDate")
            @Expose
            val distributionDate: String,

            @SerializedName("NewsId")
            @Expose
            val newsId: String,

            @SerializedName("NewsRelation")
            @Expose
            val newsRelation: List<NewsRelation>,

            @SerializedName("NewsType")
            @Expose
            val newsType: Int,

            @SerializedName("Sapo")
            @Expose
            val sapo: String,

            @SerializedName("ShareCount")
            @Expose
            val shareCount: Int,

            @SerializedName("Source")
            @Expose
            val source: String,

            @SerializedName("SourceLink")
            @Expose
            val sourceLink: String,

            @SerializedName("SubTitle")
            @Expose
            val subTitle: String,

            @SerializedName("ThreadId")
            @Expose
            val threadId: Int,

            @SerializedName("ThreadName")
            @Expose
            val threadName: String,

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
            val zoneId: Int,

            @SerializedName("ZoneName")
            @Expose
            val zoneName: String
        ) {
            data class NewsRelation(
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
            )
        }
    }

    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class News(
        @SerializedName("Data")
        @Expose
        val data: List<Any>,

        @SerializedName("TagsInfo")
        @Expose
        val tagsInfo: TagsInfo
    ) {
        data class TagsInfo(
            @SerializedName("Id")
            @Expose
            val id: Int
        )
    }
}