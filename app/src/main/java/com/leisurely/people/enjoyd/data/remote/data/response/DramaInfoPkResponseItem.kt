package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * 자세한 드라마 정보 리스트 API (/dramas/info/{pk}) 내
 * [DramaInfoPkResponse.dramas] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.12
 */
@Serializable
data class DramaInfoPkResponseItem(
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
