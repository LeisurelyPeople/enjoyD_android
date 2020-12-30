package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.source.DramasBookmarkRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 드라마 북마크 정보 관련 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.30
 */
class DramasBookmarkRepository(
    private val dramasBookmarkRemoteDataSource: DramasBookmarkRemoteDataSource
) {

    suspend fun getDramasBookmarks() = flow {
        emit(dramasBookmarkRemoteDataSource.getDramasBookmarks())
    }.flowOn(Dispatchers.IO)

    suspend fun deleteDramasBookmarks(dramaInfoSlug: String, episode: String) = flow {
        emit(dramasBookmarkRemoteDataSource.deleteDramasBookmarks(dramaInfoSlug, episode))
    }.flowOn(Dispatchers.IO)
}