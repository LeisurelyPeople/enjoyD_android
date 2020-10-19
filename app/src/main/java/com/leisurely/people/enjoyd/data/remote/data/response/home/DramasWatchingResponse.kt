package com.leisurely.people.enjoyd.data.remote.data.response.home

import com.google.gson.annotations.SerializedName

/**
 * 시청 중인 드라마 Api 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
data class DramasWatchingResponse(
    @SerializedName("drama_pk")
    val dramaPk: String,
    @SerializedName("video_id")
    val videoId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("default_thumbnail")
    val defaultThumbnail: String
)