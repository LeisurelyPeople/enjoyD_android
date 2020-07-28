package com.leisurely.people.enjoyd.model.api

import com.leisurely.people.enjoyd.data.remote.drama.SearchDrama
import com.leisurely.people.enjoyd.util.coroutine.apiService
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 드라마 관련 api 를 호출해 데이터를 받아오는 로직들을 명시한다.
 *
 * @author ricky
 * @since v1.0.0 / 2020.12.07
 */
object DramaApi {
    suspend fun dramaInfoSearch(
        search: String?,
        ordering: String
    ): Single<List<SearchDrama>> = withContext(Dispatchers.IO) {
        return@withContext apiService.dramasInfoSearch(search, ordering)
    }
}
