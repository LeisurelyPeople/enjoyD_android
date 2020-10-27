package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 드라마정보 디테일 API (/dramas/{drama_info_slug}) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramasSlugGetResponse(
    val pk: Int,

    val poster: String,

    val title: String,

    @SerialName("avg_rating")
    val avgRating: Double,

    val summary: String,

    val tag: List<String>,

    val cast: List<String>,

    val director: String,

    val dramas: List<DramasSlugResponseItem>
)
