package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.source.DramasBannerRemoteDataSource
import com.leisurely.people.enjoyd.util.coroutine.ResultWrapper
import com.leisurely.people.enjoyd.util.coroutine.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 드라마 배너 정보 관련 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramasBannerRepository(
    private val dramasBannerRemoteDataSource: DramasBannerRemoteDataSource
) {

    suspend fun getDramasBanner(): ResultWrapper<DramasBannerResponse> {
        return safeApiCall(Dispatchers.IO) {
            dramasBannerRemoteDataSource.getDramasBanner()
        }
    }
}