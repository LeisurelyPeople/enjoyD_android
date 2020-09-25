package com.leisurely.people.enjoyd.data.remote.data.request

import kotlinx.serialization.SerialName

/**
 * 북마크 다중 해제 API (/accounts/dramas/drama/bookmarks/) request 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.08
 */
data class DeleteAccountsDramasDramaBookmarksRequest(
    @SerialName("dramas_pk")
    val dramasPk: ArrayList<Int>
)