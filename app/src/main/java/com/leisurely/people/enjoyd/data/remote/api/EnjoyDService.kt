package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoPkResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * EnjoyD Api 들을 관리하는 인터페이스
 * 해당 인터페이스에 담는 API 들을 헤더에 토큰을 담는다.
 *
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.08
 */
interface EnjoyDService {
    /** 간략한 드라마 정보 리스트 API (/dramas/info) */
    @GET("/dramas/info/")
    fun getDramasInfo(): Single<DramaInfoResponse>

    /** 자세한 드라마 정보 리스트 API (/dramas/info/{pk}) */
    @GET("/dramas/info/{pk}/")
    fun getDramasInfoPk(@Path("pk") pk: String): Single<DramaInfoPkResponse>

    /** 드라마 정보 검색 API (/dramas/info/search) */
    @GET("/dramas/info/search/")
    fun getDramasInfoSearch(
        @Query("search") search: String?,
        @Query("order") order: String = "avg_rating"
    ): Single<DramaInfoSearchResponse>
}