package com.leisurely.people.enjoyd.data.remote.data.response

/**
 * 해당 드라마의 연관 드라마 목록 API (/dramas/{drama_info_slug}/related/search) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.11.01
 */
data class DramasSlugRelatedSearchResponse(
    val totalCount: Int,

    val next: Boolean,

    val results: List<DramasSlugRelatedSearchResponseItem>
)