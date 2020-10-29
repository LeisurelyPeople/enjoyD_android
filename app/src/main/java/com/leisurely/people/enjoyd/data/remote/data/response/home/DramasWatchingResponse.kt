package com.leisurely.people.enjoyd.data.remote.data.response.home

import com.google.gson.annotations.SerializedName

/**
 * 시청 중인 드라마 Api 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
data class DramasWatchingResponse(
    @SerializedName("slug")
    val slug: String, // 에피소드가 속하는 드라마 고유 값
    @SerializedName("episode")
    val episode: Int, // 에피소드 회차 값 (1화 = 1, 2화 = 2)
    @SerializedName("video_id")
    val videoId: String, // 유튜브 video id
    @SerializedName("episode_title")
    val episodeTitle: String, // 에피소드 제목
    @SerializedName("producer")
    val producer: String, // 드라마 프로듀서
    @SerializedName("default_thumbnail")
    val defaultThumbnail: String // 에피소드 썸네일 이미지
)