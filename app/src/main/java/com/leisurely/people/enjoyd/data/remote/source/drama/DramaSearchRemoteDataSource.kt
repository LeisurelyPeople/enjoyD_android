package com.leisurely.people.enjoyd.data.remote.source.drama

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponse
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 드라마 검색 화면에서 쓰이는 Remote DataSource 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.20
 */
class DramaSearchRemoteDataSource(private val enjoyDService: EnjoyDService) {
    suspend fun dramaInfoSearch(
        search: String?,
        ordering: String
    ): Single<DramasSearchResponse> = withContext(Dispatchers.IO) {
        return@withContext enjoyDService.getDramasSearch(search, ordering)
    }
}