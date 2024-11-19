package com.example.genknews.common.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsHomeResponse(
    @SerializedName("BoxVideo")
    @Expose
    val boxVideo: List<BoxVideo>,

    @SerializedName("Caring")
    @Expose
    val caring: Caring,

    @SerializedName("CategoriesBox")
    @Expose
    val categoriesBox: CategoriesBox,

    @SerializedName("DontForget")
    @Expose
    val dontForget: DontForget,

    @SerializedName("HomeNewsPosition")
    @Expose
    val homeNewsPosition: HomeNewsPosition,

    @SerializedName("LastUpdated")
    @Expose
    val lastUpdated: LastUpdated,

    @SerializedName("LastestNews")
    @Expose
    val lastestNews: LastestNews,

    @SerializedName("NewsByZone")
    @Expose
    val newsByZone: List<NewsByZone>,

    @SerializedName("TimeLine")
    @Expose
    val timeLine: TimeLine
){
    data class BoxVideo(
        @SerializedName("Author")
        @Expose
        val author: String,

        @SerializedName("Avatar")
        @Expose
        val avatar: String,

        @SerializedName("Capacity")
        @Expose
        val capacity: Int,

        @SerializedName("CommentCount")
        @Expose
        val commentCount: Int,

        @SerializedName("Description")
        @Expose
        val description: String,

        @SerializedName("DistributionDate")
        @Expose
        val distributionDate: String,

        @SerializedName("Duration")
        @Expose
        val duration: String,

        @SerializedName("FileName")
        @Expose
        val fileName: String,

        @SerializedName("HtmlCode")
        @Expose
        val htmlCode: String,

        @SerializedName("Id")
        @Expose
        val id: Int,

        @SerializedName("KeyVideo")
        @Expose
        val keyVideo: String,

        @SerializedName("Name")
        @Expose
        val name: String,

        @SerializedName("PName")
        @Expose
        val pName: String,

        @SerializedName("ShareCount")
        @Expose
        val shareCount: Int,

        @SerializedName("Size")
        @Expose
        val size: Size,

        @SerializedName("SocialCount")
        @Expose
        val socialCount: Int,

        @SerializedName("Source")
        @Expose
        val source: String,

        @SerializedName("SourceLink")
        @Expose
        val sourceLink: String,

        @SerializedName("Tags")
        @Expose
        val tags: String,

        @SerializedName("Url")
        @Expose
        val url: String,

        @SerializedName("VideoRelation")
        @Expose
        val videoRelation: String,

        @SerializedName("Views")
        @Expose
        val views: Int,

        @SerializedName("ZoneId")
        @Expose
        val zoneId: Int,

        @SerializedName("ZoneName")
        @Expose
        val zoneName: String,

        @SerializedName("ZoneUrl")
        @Expose
        val zoneUrl: String
    )

    data class Size(
        @SerializedName("height")
        @Expose
        val height: Int,

        @SerializedName("width")
        @Expose
        val width: Int
    )

    data class Caring(
        @SerializedName("Data")
        @Expose
        val data: List<Data>,

        @SerializedName("Settings")
        @Expose
        val settings: Settings
    ) {
        data class Data(
            @SerializedName("Author")
            @Expose
            val author: String,

            @SerializedName("Avatar")
            @Expose
            val avatar: String,

            @SerializedName("AvatarPreload")
            @Expose
            val avatarPreload: String,

            @SerializedName("DistributionDate")
            @Expose
            val distributionDate: String,

            @SerializedName("InitSapo")
            @Expose
            val initSapo: String,

            @SerializedName("NewsId")
            @Expose
            val newsId: String,

            @SerializedName("NewsType")
            @Expose
            val newsType: Int,

            @SerializedName("Sapo")
            @Expose
            val sapo: String,

            @SerializedName("SocialCount")
            @Expose
            val socialCount: Int,

            @SerializedName("SocialType")
            @Expose
            val socialType: String,

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
        )

        data class Settings(
            @SerializedName("AttachToTimeline")
            @Expose
            val attachToTimeline: Int,

            @SerializedName("TimelinePosition")
            @Expose
            val timelinePosition: Int
        )
    }

    data class CategoriesBox(
        @SerializedName("Data")
        @Expose
        val data: List<Data>
    ) {
        data class Data(
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


    data class DontForget(
        @SerializedName("Data")
        @Expose
        val data: List<Data>,

        @SerializedName("Settings")
        @Expose
        val settings: Settings
    ) {
        data class Data(
            @SerializedName("Author")
            @Expose
            val author: String,

            @SerializedName("Avatar")
            @Expose
            val avatar: String,

            @SerializedName("AvatarPreload")
            @Expose
            val avatarPreload: String,

            @SerializedName("DistributionDate")
            @Expose
            val distributionDate: String,

            @SerializedName("InitSapo")
            @Expose
            val initSapo: String,

            @SerializedName("NewsId")
            @Expose
            val newsId: String,

            @SerializedName("NewsType")
            @Expose
            val newsType: Int,

            @SerializedName("Sapo")
            @Expose
            val sapo: String,

            @SerializedName("SocialCount")
            @Expose
            val socialCount: Int,

            @SerializedName("SocialType")
            @Expose
            val socialType: String,

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

        data class Settings(
            @SerializedName("AttachToTimeline")
            @Expose
            val attachToTimeline: Int,

            @SerializedName("TimelinePosition")
            @Expose
            val timelinePosition: Int
        )
    }

    data class HomeNewsPosition(
        @SerializedName("Data")
        @Expose
        val data: MutableList<NewsHome>
    )


    data class LastUpdated(
        @SerializedName("Data")
        @Expose
        val data: String
    )

    data class LastestNews(
        @SerializedName("Data")
        @Expose
        val data: List<Data>,

        @SerializedName("Settings")
        @Expose
        val settings: Settings
    ) {
        data class Data(
            @SerializedName("Avatar")
            @Expose
            val avatar: String,

            @SerializedName("AvatarPreload")
            @Expose
            val avatarPreload: String,

            @SerializedName("CommentCount")
            @Expose
            val commentCount: Int,

            @SerializedName("DistributionDate")
            @Expose
            val distributionDate: String,

            @SerializedName("InitSapo")
            @Expose
            val initSapo: String,

            @SerializedName("NewsId")
            @Expose
            val newsId: String,

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
        )

        data class Settings(
            @SerializedName("AttachToTimeline")
            @Expose
            val attachToTimeline: Int,

            @SerializedName("Id")
            @Expose
            val id: Int,

            @SerializedName("TimelinePosition")
            @Expose
            val timelinePosition: Int
        )
    }


    data class NewsByZone(
        @SerializedName("Data")
        @Expose
        val data: List<Data>,

        @SerializedName("Settings")
        @Expose
        val settings: Settings,

        @SerializedName("Zone")
        @Expose
        val zone: Zone
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

            @SerializedName("Order")
            @Expose
            val order: Int,

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

        data class Settings(
            @SerializedName("AttachToTimeline")
            @Expose
            val attachToTimeline: Int,

            @SerializedName("DisplayStyle")
            @Expose
            val displayStyle: Int
        )

        data class Zone(
            @SerializedName("Id")
            @Expose
            val id: Int,

            @SerializedName("Name")
            @Expose
            val name: String,

            @SerializedName("ShortUrl")
            @Expose
            val shortUrl: String
        )
    }

    data class TimeLine(
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

            @SerializedName("Order")
            @Expose
            val order: Int,

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
            val zoneName: String,

            @SerializedName("ZoneShortURL")
            @Expose
            val zoneShortURL: String
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
}