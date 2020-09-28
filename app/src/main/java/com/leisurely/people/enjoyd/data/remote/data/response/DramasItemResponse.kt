package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 드라마 아이템 정보를 담고있는 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
@Serializable
data class DramasItemResponse(
    val slug: String,
    val poster: String,
    val title: String,
    val producer: String
)
