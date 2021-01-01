package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.local.source.DramaLocalDataSource
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.*
import com.leisurely.people.enjoyd.data.remote.source.DramasBookmarkRemoteDataSource
import com.leisurely.people.enjoyd.data.remote.source.DramasTagRemoteDataSource
import com.leisurely.people.enjoyd.data.remote.source.drama.DramaSearchRemoteDataSource
import com.leisurely.people.enjoyd.util.coroutine.ApiCallResultWrapper
import com.leisurely.people.enjoyd.util.coroutine.safeApiCall
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Response

/**
 * 드라마 관련 api 를 호출해 데이터를 받아와 클라이언트에서 바로 사용할 수 있도록
 * 정제하여 리턴하는 함수들을 모아놓은 Repository 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.12.07
 */
class DramaRepository(
    private val dramaSearchRemoteDataSource: DramaSearchRemoteDataSource,
    private val dramasTagRemoteDataSource: DramasTagRemoteDataSource,
    private val dramaLocalDataSource: DramaLocalDataSource,
    private val dramasBookmarkRemoteDataSource: DramasBookmarkRemoteDataSource
) {
    suspend fun getDramasSlug(
        dramaInfoSlug: String
    ): Single<DramasSlugGetResponse> = withContext(Dispatchers.IO) {
        // 아직은 로컬 영역에서 별도로 하는 작업이 없음 (더미 함수 넣어놓음)
        dramaLocalDataSource.dramaInfoSearch()

        // 리모트 영역에서 가져온 데이터 리턴
        return@withContext dramaSearchRemoteDataSource.getDramasSlug(dramaInfoSlug)
    }

    suspend fun getDramasSlugEpisodes(
        dramaInfoSlug: String
    ): Single<DramasSlugEpisodesResponse> = withContext(Dispatchers.IO) {
        // 아직은 로컬 영역에서 별도로 하는 작업이 없음

        // 리모트 영역에서 가져온 데이터 리턴
        return@withContext dramaSearchRemoteDataSource.getDramasSlugEpisodes(dramaInfoSlug)
    }

    suspend fun getDramasSlugRelatedSearch(
        dramaInfoSlug: String
    ): Single<DramasSlugRelatedSearchResponse> = withContext(Dispatchers.IO) {
        // 아직은 로컬 영역에서 별도로 하는 작업이 없음

        // 리모트 영역에서 가져온 데이터 리턴
        return@withContext dramaSearchRemoteDataSource.getDramasSlugRelatedSearch(dramaInfoSlug)
    }

    suspend fun getDramasSearch(
        search: String?,
        ordering: String
    ): Single<DramasSearchGetResponse> = withContext(Dispatchers.IO) {
        // 아직은 로컬 영역에서 별도로 하는 작업이 없음

        // 리모트 영역에서 가져온 데이터 리턴
        return@withContext dramaSearchRemoteDataSource.getDramasSearch(search, ordering)
    }

    suspend fun getDramasUsingTags(
        tag: String,
        page: Int,
        pageSize: Int
    ): ApiCallResultWrapper<PagingResponse<DramasItemResponse>> {
        return safeApiCall(Dispatchers.IO) {
            dramasTagRemoteDataSource.getDramas(tag, page, pageSize)
        }
    }

    suspend fun postAccountsDramasSlugEpisodeBookmark(
        dramaInfoSlug: String,
        episode: String
    ): ApiCallResultWrapper<ResponseBody> {
        return safeApiCall(Dispatchers.IO) {
            dramasBookmarkRemoteDataSource.postAccountsDramasSlugEpisodeBookmark(
                dramaInfoSlug, episode
            )
        }
    }

    suspend fun deleteAccountsDramasSlugEpisodeBookmark(
        dramaInfoSlug: String,
        episode: String
    ): ApiCallResultWrapper<Response<Unit?>> {
        return safeApiCall(Dispatchers.IO) {
            dramasBookmarkRemoteDataSource.deleteAccountsDramasSlugEpisodeBookmark(
                dramaInfoSlug, episode
            )
        }
    }
}
