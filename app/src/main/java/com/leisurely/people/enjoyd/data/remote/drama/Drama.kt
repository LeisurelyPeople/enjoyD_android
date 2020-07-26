package com.leisurely.people.enjoyd.data.remote.drama

import kotlinx.serialization.Serializable

/**
 * 간략한 드라마 정보 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class Drama(
    val pk: Int?,

    val poster: String?,

    val title: String?
)
