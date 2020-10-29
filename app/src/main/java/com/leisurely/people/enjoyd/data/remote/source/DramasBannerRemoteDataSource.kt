package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse

/**
 * 드라마 배너 정보 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramasBannerRemoteDataSource(private val enjoyDService: EnjoyDService) {

    suspend fun getDramasBanner(): DramasBannerResponse = enjoyDService.getDramasBanner()
}