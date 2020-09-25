package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 태그 리스트 API (/dramas/tag/) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.14
 */
@Serializable
data class DramaTagGetResponse(
    val count: Int?,
    val next: String?,
    val previous: String?,
    val results: List<DramaTagResponseItem>?
)