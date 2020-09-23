package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 자세한 드라마 정보 리스트 API (/dramas/{drama_info_slug}) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramasSlugResponse(
    val pk: Int,

    val poster: String,

    val title: String,

    @SerialName("avg_rating")
    val avgRating: Int,

    val summary: String,

    val tag: List<String>,

    val cast: List<String>,

    val director: String,

    val dramas: List<DramasSlugResponseItem>
)
