package com.leisurely.people.enjoyd.data.remote.drama

import kotlinx.serialization.Serializable

/**
 * 간략한 드라마 정보 리스트 API (/dramas/info) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramaInfoResponse(
    val count: Int?,

    val next: String?,

    val previous: String?,

    val results: List<Drama>?
)
