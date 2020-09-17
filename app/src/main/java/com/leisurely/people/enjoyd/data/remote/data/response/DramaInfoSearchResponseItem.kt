package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 드라마 정보 검색 API (/dramas/info/search/) 내
 * [DramaInfoSearchResponse] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.08
 */
@Serializable
data class DramaInfoSearchResponseItem(
    val id: Int,

    val poster: String,

    val title: String
)