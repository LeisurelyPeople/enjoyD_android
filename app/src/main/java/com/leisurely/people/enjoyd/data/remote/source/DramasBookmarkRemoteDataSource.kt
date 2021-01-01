package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * 북마크 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class DramasBookmarkRemoteDataSource(private val enjoyDService: EnjoyDService) {
    suspend fun postAccountsDramasSlugEpisodeBookmark(
        dramaInfoSlug: String,
        episode: String
    ): ResponseBody {
        return enjoyDService.postAccountsDramasSlugEpisodeBookmark(dramaInfoSlug, episode)
    }

    suspend fun deleteAccountsDramasSlugEpisodeBookmark(
        dramaInfoSlug: String,
        episode: String
    ): Response<Unit?> {
        return enjoyDService.deleteAccountsDramasSlugEpisodeBookmark(dramaInfoSlug, episode)
    }
}
