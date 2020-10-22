package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasWatchingResponse
import com.leisurely.people.enjoyd.data.remote.source.DramasWatchingRemoteDataSource
import com.leisurely.people.enjoyd.util.coroutine.ApiCallResultWrapper
import com.leisurely.people.enjoyd.util.coroutine.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 홈화면에 있는 시청중인 드라마 정보들을 가져오는 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class DramasWatchingRepository(
    private val dramasWatchingRemoteDataSource: DramasWatchingRemoteDataSource
) {
    suspend fun getDramasWatching(
        page: Int,
        size: Int
    ): ApiCallResultWrapper<PagingResponse<DramasWatchingResponse>> {
        return safeApiCall(Dispatchers.IO) {
            dramasWatchingRemoteDataSource.getDramasWatching(page, size)
        }
    }

    suspend fun getDramasWatchingUsingFlow(page: Int, size: Int) = flow {
        emit(dramasWatchingRemoteDataSource.getDramasWatching(page, size))
    }.flowOn(Dispatchers.IO)
}