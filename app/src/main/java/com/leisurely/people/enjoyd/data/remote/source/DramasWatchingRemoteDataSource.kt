package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasWatchingResponse

/**
 * 홈화면에 있는 시청중인 드라마 정보들을 가져오는 API 와 관련된 DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class DramasWatchingRemoteDataSource(private val enjoyDService: EnjoyDService) {

    suspend fun getDramasWatching(page: Int, size: Int): PagingResponse<DramasWatchingResponse> {
        return enjoyDService.getDramasWatching(page, size)
    }
}