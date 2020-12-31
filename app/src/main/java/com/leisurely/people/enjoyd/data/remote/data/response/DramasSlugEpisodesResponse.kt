package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 해당 드라마의 에피소드 목록 API (/dramas/{drama_info_slug}/episodes}) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.11.01
 */
@Serializable
data class DramasSlugEpisodesResponse(
    val totalCount: Int,

    val next: Boolean,

    val results: List<DramasSlugEpisodesResponseItem>
)