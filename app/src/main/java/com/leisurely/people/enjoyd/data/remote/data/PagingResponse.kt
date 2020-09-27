package com.leisurely.people.enjoyd.data.remote.data

/**
 * 페이징 관련 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.10
 */
data class PagingResponse<T>(
    val totalCount: Int,
    val next: Boolean,
    val results: List<T>
)