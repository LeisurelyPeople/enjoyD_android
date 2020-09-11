package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import io.reactivex.Single
import retrofit2.http.GET
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

    @GET("dramas/info/rating/")
    fun getDramasInfoRating(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResponse<DramaEvaluationResponse>>

}