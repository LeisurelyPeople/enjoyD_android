package com.leisurely.people.enjoyd.data.remote.data.request.evaluation

import com.google.gson.annotations.SerializedName

/**
 * 드라마 평가를 위한 서버 request 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.15
 */
data class DramaEvaluationRequest(
    @SerializedName("rating")
    var rating: Float,
    @SerializedName("drama_info_pk")
    val dramaInfoPk: Int
)