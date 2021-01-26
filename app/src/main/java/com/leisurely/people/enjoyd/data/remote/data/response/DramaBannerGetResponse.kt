package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 드라마 정보 배너 API (/dramas/info/banner/) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.14
 */
@Serializable
data class DramaBannerGetResponse(
    val slug: String,

    val poster: String,

    val producer: String,

    val title: String,

    val tag: List<String>
)