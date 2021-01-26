package com.leisurely.people.enjoyd.data.remote.data.response

import kotlinx.serialization.Serializable

/**
 * 북마크 리스트 API (/accounts/dramas/drama/bookmarks/) 응답 데이터 구조
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.14
 */
@Serializable
class AccountsDramasDramaBookmarksGetResponse : ArrayList<DramaBookmarkPkResponseItem>()