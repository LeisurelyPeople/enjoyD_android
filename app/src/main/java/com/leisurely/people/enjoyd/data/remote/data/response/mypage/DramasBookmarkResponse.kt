package com.leisurely.people.enjoyd.data.remote.data.response.mypage

import com.google.gson.annotations.SerializedName

/**
 * 북마크 된 드라마 API 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.30
 */
data class DramasBookmarkResponse(
    @SerializedName("default_thumbnail")
    val defaultThumbnail: String, // 썸네일
    @SerializedName("drama_info_title")
    val dramaInfoTitle: String, // 드라마 제목
    @SerializedName("episode")
    val episode: Int, // 회차
    @SerializedName("title")
    val title: String, // 에피소드 제목
    @SerializedName("video_id")
    val videoId: String, // 유튜브 video Id
    @SerializedName("drama_info_slug")
    val dramaInfoSlug: String // 에피소드가 속하는 드라마 고유 값
)