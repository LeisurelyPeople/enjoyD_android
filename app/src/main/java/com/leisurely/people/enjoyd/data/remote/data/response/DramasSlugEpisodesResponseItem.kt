package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.SerialName

/**
 * 해당 드라마의 에피소드 목록 API (/dramas/{drama_info_slug}/episodes}) 응답 아이템 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.11.01
 */
data class DramasSlugEpisodesResponseItem(
    @SerialName("default_thumbnail")
    val defaultThumbnail: String,

    val episode: Int,

    @SerialName("episode_title")
    val episodeTitle: String,

    @SerialName("is_bookmark")
    val isBookmark: Boolean,

    val producer: String,

    val slug: String,

    @SerialName("video_id")
    val videoId: String
)