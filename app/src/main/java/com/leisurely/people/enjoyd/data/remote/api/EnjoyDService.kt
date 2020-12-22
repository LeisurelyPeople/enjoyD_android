package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponse
import com.leisurely.people.enjoyd.data.remote.data.response.UserResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasWatchingResponse
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

    /** 사용자 정보 가져오는 API */
    @GET("accounts/")
    suspend fun getAccounts(): UserResponse

    /** 드라마 평가를 위한 데이터 가져오는 API */
    @GET("accounts/dramas/ratings/")
    fun getDramasRatings(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResponse<DramaEvaluationResponse>>

    /** 드라마 평가를 위한 데이터 가져오는 API (코루틴용) */
    @GET("accounts/dramas/ratings/")
    suspend fun getDramasRatingsUsingCoroutine(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): PagingResponse<DramaEvaluationResponse>

    /** 드라마 평가한 데이터 서버로 보내는 API */
    @POST("accounts/dramas/ratings/")
    fun postDramasRatings(@Body data: HashMap<String, List<DramaEvaluationRequest>>): Completable

    /** 드라마 평가한 데이터 서버로 보내는 API (코루틴용) */
    @POST("accounts/dramas/ratings/")
    suspend fun postDramasRatingsUsingCoroutine(
        @Body data: HashMap<String, List<DramaEvaluationRequest>>
    )

    /** 간략한 드라마 정보 리스트 API (/dramas) */
    @GET("/dramas/")
    suspend fun getDramas(
        @Query("tag") tag: String,
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): PagingResponse<DramasItemResponse>

    /** 자세한 드라마 정보 리스트 API (/dramas/{drama_info_slug}) */
    @GET("/dramas/{drama_info_slug}/")
    fun getDramasSlug(
        @Path("drama_info_slug") dramaInfoSlug: String
    ): Single<DramasSlugResponse>

    /** 드라마 정보 검색 API (/dramas/search) */
    @GET("/dramas/search/")
    fun getDramasSearch(
        @Query("search") search: String?,
        @Query("order") order: String = "avg_rating"
    ): Single<DramasSearchResponse>

    /** 드라마 배너 조회 API (/drmas/banner/) */
    @GET("/dramas/banner/")
    suspend fun getDramasBanner(): DramasBannerResponse

    /** 드라마 태그 조회 API (/drmas/banner/) */
    @GET("/dramas/tags/")
    suspend fun getDramasTags(): PagingResponse<DramasTagsResponse>

    @GET("accounts/dramas/watching/")
    suspend fun getDramasWatching(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): PagingResponse<DramasWatchingResponse>
}