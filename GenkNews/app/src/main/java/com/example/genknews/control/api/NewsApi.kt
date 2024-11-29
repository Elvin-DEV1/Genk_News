package com.example.genknews.control.api

import com.example.genknews.common.entity.NewsHomeResponse
import com.example.genknews.common.entity.HotVideoResponse
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.entity.NewsLatestResponse
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.entity.TagResponse
import com.example.genknews.common.entity.VideoDetailResponse
import com.example.genknews.common.entity.VideoZoneResponse
import com.example.genknews.common.entity.CategoryNewsResponse
import com.example.genknews.common.utils.Constants.Companion.API_KEY
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface NewsAPI {
    @POST("app/home")
    @FormUrlEncoded
    suspend fun getHome(
        @Header("os") os: String = "android",
        @Header("version") version: String = "133",
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<NewsHomeResponse>

    @POST("app/lastest-news-paging")
    @FormUrlEncoded
    suspend fun getLatestNews(
        @Field("secret_key") secretKey: String = API_KEY,
        @Field("page_index") pageIndex: String = "2",
        @Field("page-size") pageSize: String = "10"
    ): Response<NewsLatestResponse>

    @POST("app/menu")
    @FormUrlEncoded
    suspend fun getMenu(
        @Field("secret_key") secretKey: String = API_KEY
    ): Response<MenuResponse>

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
        @Field("zone_id") zoneId: String
    ): Response<CategoryNewsResponse>

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
        @SerializedName("keywords") val keywords: String,
        @SerializedName("secret_key") val secretKey: String = API_KEY,
        @SerializedName("page_index") val pageIndex: String = "1",
        @SerializedName("page_size") val pageSize: String = "20"
    )
}