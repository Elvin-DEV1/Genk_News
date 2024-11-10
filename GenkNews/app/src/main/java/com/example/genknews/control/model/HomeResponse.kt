package com.example.genknews.control.model

data class HomeResponse(
    val boxVideo: List<BoxVideo>,
    val caring: Caring,
    val categoriesBox: CategoriesBox,
    val dontForget: DontForget,
    val homeNewsPosition: HomeNewsPosition,
    val lastUpdated: LastUpdated,
    val lastestNews: LastestNews,
    val newsByZone: List<NewsByZone>,
    val timeLine: TimeLine
)

data class BoxVideo(
    val author: String,
    val avatar: String,
    val capacity: Int,
    val commentCount: Int,
    val description: String,
    val distributionDate: String,
    val duration: String,
    val fileName: String,
    val htmlCode: String,
    val id: Int,
    val keyVideo: String,
    val name: String,
    val pName: String,
    val shareCount: Int,
    val size: Size,
    val socialCount: Int,
    val source: String,
    val sourceLink: String,
    val tags: String,
    val url: String,
    val videoRelation: String,
    val views: Int,
    val zoneId: Int,
    val zoneName: String,
    val zoneUrl: String
)

data class Size(
    val height: Int,
    val width: Int
)

data class Caring(
    val data: List<DataCaring>,
    val settings: SettingsCaring
)

data class DataCaring(
    val author: String,
    val avatar: String,
    val avatarPreload: String,
    val distributionDate: String,
    val initSapo: String,
    val newsId: String,
    val newsType: Int,
    val sapo: String,
    val socialCount: Int,
    val socialType: String,
    val source: String,
    val sourceLink: String,
    val subTitle: String,
    val threadId: Int,
    val title: String,
    val type: Int,
    val url: String,
    val zoneId: Int,
    val zoneName: String
)

data class SettingsCaring(
    val attachToTimeline: Int,
    val timelinePosition: Int
)

data class CategoriesBox(
    val data: List<DataCategoriesBox>
)

data class DataCategoriesBox(
    val domain: String,
    val id: Int,
    val logo: String,
    val name: String,
    val shortURL: String
)

data class DontForget(
    val Data: List<DataDontForget>,
    val Settings: SettingsDontForget
)

data class DataDontForget(
    val Author: String,
    val Avatar: String,
    val AvatarPreload: String,
    val DistributionDate: String,
    val InitSapo: String,
    val NewsId: String,
    val NewsType: Int,
    val Sapo: String,
    val SocialCount: Int,
    val SocialType: String,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)

data class SettingsDontForget(
    val AttachToTimeline: Int,
    val TimelinePosition: Int
)

data class HomeNewsPosition(
    val Data: List<DataHomeNewsPosition>
)

data class DataHomeNewsPosition(
    val Avatar: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val NewsId: String,
    val NewsRelation: List<NewsRelationHomeNewsPosition>,
    val NewsType: Int,
    val Order: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val ThreadName: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneShortURL: String
)

data class NewsRelationHomeNewsPosition(
    val Author: String,
    val Avatar: String,
    val Avatar1: String,
    val Avatar2: String,
    val Avatar3: String,
    val Avatar4: String,
    val Avatar5: String,
    val AvatarPreload: String,
    val DisplayStyle: Int,
    val DistributionDate: String,
    val NewsId: Long,
    val NewsRelationType: Int,
    val ObjectType: Int,
    val Sapo: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)

data class LastUpdated(
    val Data: String
)

data class LastestNews(
    val Data: List<DataLastestNews>,
    val Settings: SettingsLastestNews
)

data class DataLastestNews(
    val Avatar: String,
    val AvatarPreload: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val InitSapo: String,
    val NewsId: String,
    val NewsType: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String
)

data class SettingsLastestNews(
    val AttachToTimeline: Int,
    val Id: Int,
    val TimelinePosition: Int
)

data class NewsByZone(
    val Data: List<DataNewsByZone>,
    val Settings: SettingsNewsByZone,
    val Zone: ZoneNewsByZone
)

data class DataNewsByZone(
    val Avatar: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val NewsId: String,
    val NewsRelation: List<NewsRelationNewsByZone>,
    val NewsType: Int,
    val Order: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)

data class NewsRelationNewsByZone(
    val Author: String,
    val Avatar: String,
    val Avatar1: String,
    val Avatar2: String,
    val Avatar3: String,
    val Avatar4: String,
    val Avatar5: String,
    val AvatarPreload: String,
    val DisplayStyle: Int,
    val DistributionDate: String,
    val NewsId: Long,
    val NewsRelationType: Int,
    val ObjectType: Int,
    val Sapo: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)

data class SettingsNewsByZone(
    val AttachToTimeline: Int,
    val DisplayStyle: Int
)

data class ZoneNewsByZone(
    val Id: Int,
    val Name: String,
    val ShortUrl: String
)

data class TimeLine(
    val Data: List<DataTimeLine>
)

data class DataTimeLine(
    val Avatar: String,
    val CommentCount: Int,
    val DistributionDate: String,
    val NewsId: String,
    val NewsRelation: List<NewsRelationTimeLine>,
    val NewsType: Int,
    val Order: Int,
    val Sapo: String,
    val ShareCount: Int,
    val Source: String,
    val SourceLink: String,
    val SubTitle: String,
    val ThreadId: Int,
    val ThreadName: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int,
    val ZoneName: String,
    val ZoneShortURL: String
)

data class NewsRelationTimeLine(
    val Author: String,
    val Avatar: String,
    val Avatar1: String,
    val Avatar2: String,
    val Avatar3: String,
    val Avatar4: String,
    val Avatar5: String,
    val AvatarPreload: String,
    val DisplayStyle: Int,
    val DistributionDate: String,
    val NewsId: Long,
    val NewsRelationType: Int,
    val ObjectType: Int,
    val Sapo: String,
    val Title: String,
    val Type: Int,
    val Url: String,
    val ZoneId: Int
)