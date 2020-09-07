package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.APIService
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoSearchResponse
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 계정 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class DramaRemoteDataSource(private val apiService: APIService) {
    suspend fun dramaInfoSearch(
        search: String?,
        ordering: String
    ): Single<DramaInfoSearchResponse> = withContext(Dispatchers.IO) {
        return@withContext apiService.getDramasInfoSearch(search, ordering)
    }
}