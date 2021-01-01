package com.leisurely.people.enjoyd.data.remote.data.response.home

import com.google.gson.annotations.SerializedName

/**
 * 홈화면에 있는 드라마 배너 정보 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
data class DramasBannerResponse(
    val slug: String,
    val poster: String,
    val producer: String,
    val title: String,
    val tag: List<String>,
    @SerializedName("episode_count")
    val episodeCount: Int
)