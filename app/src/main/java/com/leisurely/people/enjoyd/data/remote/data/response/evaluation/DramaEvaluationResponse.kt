package com.leisurely.people.enjoyd.data.remote.data.response.evaluation


import com.google.gson.annotations.SerializedName

/**
 * 드라마 평가 관련 모델 클래스
 * 서버 응답값
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.11
 */
data class DramaEvaluationResponse(
    @SerializedName("avg_rating")
    val avgRating: Float,
    @SerializedName("pk")
    val pk: Int,
    @SerializedName("poster")
    val poster: String,
    @SerializedName("release_year")
    val releaseYear: Int,
    @SerializedName("title")
    val title: String
)