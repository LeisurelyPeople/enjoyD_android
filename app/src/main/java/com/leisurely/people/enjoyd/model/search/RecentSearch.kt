package com.leisurely.people.enjoyd.model.search

import kotlinx.serialization.Serializable

/**
 * 최근 검색한 내용에 대한 데이터 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.20
 */
@Serializable
data class RecentSearch(
    val id: Long,
    val title: String
)
