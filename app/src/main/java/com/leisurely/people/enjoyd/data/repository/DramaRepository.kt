package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.local.source.DramaLocalDataSource
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponse
import com.leisurely.people.enjoyd.data.remote.source.DramasTagRemoteDataSource
import com.leisurely.people.enjoyd.data.remote.source.drama.DramaSearchRemoteDataSource
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    private val dramaLocalDataSource: DramaLocalDataSource
) {
    suspend fun dramaInfoSearch(
        search: String?,
        ordering: String
    ): Single<DramasSearchResponse> = withContext(Dispatchers.IO) {
        // 아직은 로컬 영역에서 별도로 하는 작업이 없음 (더미 함수 넣어놓음)
        dramaLocalDataSource.dramaInfoSearch()

        // 리모트 영역에서 가져온 데이터 리턴
        return@withContext dramaSearchRemoteDataSource.dramaInfoSearch(search, ordering)
    }

    suspend fun getDramasUsingTags(
        tag: String,
        page: Int,
        pageSize: Int
    ): PagingResponse<DramasItemResponse> {
        return dramasTagRemoteDataSource.getDramas(tag, page, pageSize)
    }
}
