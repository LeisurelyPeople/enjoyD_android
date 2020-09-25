package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 드라마정보리스트API API (/dramas) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramasGetResponse(
    val count: Int,

    val next: String,

    val previous: String,

    val results: List<DramasResponseItem>
)
