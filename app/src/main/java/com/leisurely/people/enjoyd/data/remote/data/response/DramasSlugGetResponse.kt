package com.leisurely.people.enjoyd.data.remote.data.response

import com.google.gson.annotations.SerializedName
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
    val slug: String,

    val poster: String,

    val title: String,

    @SerializedName("avg_rating")
    @SerialName("avg_rating")
    val avgRating: Float,

    @SerializedName("release_year")
    @SerialName("release_year")
    val releaseYear: Int,

    val summary: String,

    val tag: List<String>,

    val cast: List<String>,

    val writer: String
)
