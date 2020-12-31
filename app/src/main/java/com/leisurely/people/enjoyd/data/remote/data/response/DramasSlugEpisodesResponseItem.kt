package com.leisurely.people.enjoyd.data.remote.data.response

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 해당 드라마의 에피소드 목록 API (/dramas/{drama_info_slug}/episodes}) 응답 아이템 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.11.01
 */
@Serializable
data class DramasSlugEpisodesResponseItem(
    @SerializedName("default_thumbnail")
    @SerialName("default_thumbnail")
    val defaultThumbnail: String,

    val episode: Int,

    @SerializedName("episode_title")
    @SerialName("episode_title")
    val episodeTitle: String,

    @SerializedName("is_bookmark")
    @SerialName("is_bookmark")
    val isBookmark: Boolean,

    val producer: String,

    val slug: String,

    @SerializedName("video_id")
    @SerialName("video_id")
    val videoId: String
)