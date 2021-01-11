package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.source.DramasBookmarkRemoteDataSource
import com.leisurely.people.enjoyd.util.coroutine.ApiCallResultWrapper
import com.leisurely.people.enjoyd.util.coroutine.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

/**
 * 드라마 북마크 정보 관련 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.30
 */
class DramasBookmarkRepository(
    private val dramasBookmarkRemoteDataSource: DramasBookmarkRemoteDataSource
) {
    suspend fun getDramasBookmarks(page: Int, pageSize: Int) = flow {
        emit(dramasBookmarkRemoteDataSource.getDramasBookmarks(page, pageSize))
    }.flowOn(Dispatchers.IO)

    suspend fun postDramasBookmark(dramaInfoSlug: String, episode: String)= flow<ResponseBody>{
        emit(dramasBookmarkRemoteDataSource.postDramasBookmark(dramaInfoSlug, episode))
    }.flowOn(Dispatchers.IO)

    suspend fun deleteDramasBookmark(dramaInfoSlug: String, episode: String) = flow {
        emit(dramasBookmarkRemoteDataSource.deleteDramasBookmark(dramaInfoSlug, episode))
    }.flowOn(Dispatchers.IO)
}
