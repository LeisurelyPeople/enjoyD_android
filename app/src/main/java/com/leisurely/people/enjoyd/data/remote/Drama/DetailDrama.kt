package com.leisurely.people.enjoyd.data.remote.Drama

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 자세한 드라마 정보 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DetailDrama(
    val pk: String?,

    @SerialName("video_id")
    val videoId: String?,

    val title: String?,

    val episode: Int?,

    val summary: String?,

    val duration: Int?,

    @SerialName("small_thumbnail")
    val smallThumbnail: String?
)
