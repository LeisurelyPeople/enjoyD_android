package com.leisurely.people.enjoyd.data.remote.Drama

import kotlinx.serialization.Serializable

/**
 * 드라마 검색 아이템 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class SearchDrama(
    val id: Int,
    val title: String
)
