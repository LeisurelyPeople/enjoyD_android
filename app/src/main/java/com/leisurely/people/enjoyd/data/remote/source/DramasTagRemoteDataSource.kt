package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse

/**
 * 홈화면에 있는 드라마 태그 정보들을 가져오는 API 와 관련된 DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramasTagRemoteDataSource(private val enjoyDService: EnjoyDService) {

    suspend fun getDramasTags(): PagingResponse<DramasTagsResponse> {
        return enjoyDService.getDramasTags()
    }
}