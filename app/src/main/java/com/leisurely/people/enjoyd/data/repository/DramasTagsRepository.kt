package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.remote.source.DramasTagRemoteDataSource
import com.leisurely.people.enjoyd.util.coroutine.ApiCallResultWrapper
import com.leisurely.people.enjoyd.util.coroutine.safeApiCall
import kotlinx.coroutines.Dispatchers

/**
 * 홈화면에 있는 드라마 태그 정보들을 가져오는 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramasTagsRepository(private val dramasTagRemoteDataSource: DramasTagRemoteDataSource) {

    suspend fun getDramasTags(): ApiCallResultWrapper<PagingResponse<DramasTagsResponse>> {
        return safeApiCall(Dispatchers.IO) {
            dramasTagRemoteDataSource.getDramasTags()
        }
    }
}