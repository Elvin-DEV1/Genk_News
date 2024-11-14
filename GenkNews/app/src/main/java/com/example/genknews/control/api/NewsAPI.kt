package com.example.genknews.control.api

import androidx.room.Query
import com.example.genknews.common.entity.ConfigResponse
import com.example.genknews.common.entity.HomeResponse
import com.example.genknews.common.entity.HotVideoResponse
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.entity.NewsResponse
import com.example.genknews.common.entity.NotifyResponse
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.entity.TagResponse
import com.example.genknews.common.entity.VideoDetailResponse
import com.example.genknews.common.entity.VideoZoneResponse
import com.example.genknews.common.entity.ZoneNewsResponse
import com.example.genknews.common.entity.ZoneResponse
import com.example.genknews.common.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface NewsAPI {
    @POST("app/home")
    suspend fun getHome(
        @Header("os") os: String,
        @Header("version") version: String,
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<HomeResponse>

    @POST("app/lastest-news-paging")
    @FormUrlEncoded
    suspend fun getLatestNews(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("page_index") pageIndex: Int,
        @Field("page-size") pageSize: Int
    ): Response<NewsResponse>

    @POST("app/menu")
    @FormUrlEncoded
    suspend fun getMenu(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<MenuResponse>

    @POST("app/config")
    @FormUrlEncoded
    suspend fun getConfig(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<ConfigResponse>

    @POST("app/get-notify")
    @FormUrlEncoded
    suspend fun getNotify(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("device_id") deviceId: String,
        @Field("user_id") userId: String
    ): Response<NotifyResponse>

    @POST("app/all-zone")
    @FormUrlEncoded
    suspend fun getAllZones(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<ZoneResponse>

    @POST("app/tag")
    @FormUrlEncoded
    suspend fun getTag(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("page_index") pageIndex: Int,
        @Field("id") id: String
    ): Response<TagResponse>

    @POST("app/video/zone")
    @FormUrlEncoded
    suspend fun getVideoZones(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<VideoZoneResponse>

    @POST("app/zone")
    @FormUrlEncoded
    suspend fun getZoneNews(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("page_index") pageIndex: Int,
        @Field("zone_id") zoneId: String
    ): Response<ZoneNewsResponse>

    @POST("app/video/hot")
    @FormUrlEncoded
    suspend fun getHotVideos(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<HotVideoResponse>

    @POST("app/video/detail")
    @FormUrlEncoded
    suspend fun getVideoDetail(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("video_id") videoId: String
    ): Response<VideoDetailResponse>

    @POST("app/search")
    suspend fun search(
        @Body searchRequest: SearchRequest
    ): Response<SearchResponse>

    data class SearchRequest(
        val keywords: String,
        val secretKey: String = API_KEY,
        val pageIndex: String,
        val pageSize: String
    )
}