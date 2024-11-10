package com.example.genknews.control.api

import com.example.genknews.control.model.ConfigResponse
import com.example.genknews.control.model.HomeResponse
import com.example.genknews.control.model.HotVideoResponse
import com.example.genknews.control.model.MenuResponse
import com.example.genknews.control.model.NewsResponse
import com.example.genknews.control.model.NotifyResponse
import com.example.genknews.control.model.SearchResponse
import com.example.genknews.control.model.TagResponse
import com.example.genknews.control.model.VideoDetailResponse
import com.example.genknews.control.model.VideoZoneResponse
import com.example.genknews.control.model.ZoneNewsResponse
import com.example.genknews.control.model.ZoneResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface NewsAPI {
    @POST("app/home")
    suspend fun getHome(
        @Header("os") os: String = "android",
        @Header("version") version: String = "133",
        @Field("secret_key") secretKey: String
    ): Response<HomeResponse>

    @POST("app/lastest-news-paging")
    @FormUrlEncoded
    suspend fun getLatestNews(
        @Field("secret_key") secretKey: String,
        @Field("page_index") pageIndex: Int,
        @Field("page-size") pageSize: Int = 10
    ): Response<NewsResponse>

    @POST("app/menu")
    @FormUrlEncoded
    suspend fun getMenu(
        @Field("secret_key") secretKey: String
    ): Response<MenuResponse>

    @POST("app/config")
    @FormUrlEncoded
    suspend fun getConfig(
        @Field("secret_key") secretKey: String
    ): Response<ConfigResponse>

    @POST("app/get-notify")
    @FormUrlEncoded
    suspend fun getNotify(
        @Field("secret_key") secretKey: String,
        @Field("device_id") deviceId: String,
        @Field("user_id") userId: String = "-1"
    ): Response<NotifyResponse>

    @POST("app/all-zone")
    @FormUrlEncoded
    suspend fun getAllZones(
        @Field("secret_key") secretKey: String
    ): Response<ZoneResponse>

    @POST("app/tag")
    @FormUrlEncoded
    suspend fun getTag(
        @Field("secret_key") secretKey: String,
        @Field("page_index") pageIndex: Int,
        @Field("id") id: String
    ): Response<TagResponse>

    @POST("app/video/zone")
    @FormUrlEncoded
    suspend fun getVideoZones(
        @Field("secret_key") secretKey: String
    ): Response<VideoZoneResponse>

    @POST("app/zone")
    @FormUrlEncoded
    suspend fun getZoneNews(
        @Field("secret_key") secretKey: String,
        @Field("page_index") pageIndex: Int,
        @Field("zone_id") zoneId: String
    ): Response<ZoneNewsResponse>

    @POST("app/video/hot")
    @FormUrlEncoded
    suspend fun getHotVideos(
        @Field("secret_key") secretKey: String
    ): Response<HotVideoResponse>

    @POST("app/video/detail")
    @FormUrlEncoded
    suspend fun getVideoDetail(
        @Field("secret_key") secretKey: String,
        @Field("video_id") videoId: String
    ): Response<VideoDetailResponse>

    @POST("app/search")
    suspend fun search(
        @Body searchRequest: SearchRequest
    ): Response<SearchResponse>

    data class SearchRequest(
        val keywords: String,
        val secretKey: String,
        val pageIndex: String,
        val pageSize: String = "20"
    )
}