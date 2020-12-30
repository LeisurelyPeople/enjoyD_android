package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.response.mypage.DramasBookmarkResponse

/**
 * 드라마 북마크 정보 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.30
 */
class DramasBookmarkRemoteDataSource(private val enjoyDService: EnjoyDService) {
    suspend fun getDramasBookmarks(): List<DramasBookmarkResponse> =
        enjoyDService.getDramasBookmarks()

    suspend fun deleteDramasBookmark(dramaInfoSlug: String, episode: String) =
        enjoyDService.deleteDramasBookmark(dramaInfoSlug, episode)
}