package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * "북마크 리스트 API  API (/accounts/dramas/drama/bookmarks/) 내
 * [DramaBookmarkPkResponseItem] 내에서 사용되는 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.14
 */
@Serializable
data class DramaBookmarkPkResponseItem(
    val episode: Int?,

    val pk: Int?,

    @SerialName("small_thumbnail")
    val smallThumbnail: String?,

    val title: String?
)