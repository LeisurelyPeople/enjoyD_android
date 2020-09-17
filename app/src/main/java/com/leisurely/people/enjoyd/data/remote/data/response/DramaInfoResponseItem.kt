package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 간략한 드라마 정보 리스트 API (/dramas/info) 내
 * [DramaInfoResponse.results] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramaInfoResponseItem(
    val pk: Int,

    val poster: String,

    val title: String
)
