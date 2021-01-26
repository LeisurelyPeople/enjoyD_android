package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 드라마정보리스트API (/dramas) 내
 * [DramasGetResponse.results] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramasResponseItem(
    val pk: Int,

    val poster: String,

    val title: String
)
