package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
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
    /** 드라마 평가를 위한 데이터 가져오는 API */
    @GET("accounts/dramas/ratings")
    fun getDramasRatings(
        @Query("page") page: Int,
        @Query("page_size") pageSize: Int
    ): Single<PagingResponse<DramaEvaluationResponse>>

    /** 드라마 평가한 데이터 서버로 보내는 API */
    @POST("accounts/dramas/ratings")
    fun postDramasRatings(@Body data: HashMap<String, List<DramaEvaluationRequest>>): Completable

    /** 간략한 드라마 정보 리스트 API (/dramas) */
    @GET("/dramas/")
    fun getDramas(): Single<DramasResponse>

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

    @GET("/dramas/banner/")
    suspend fun getDramasBanner(): DramasBannerResponse

    @GET("/dramas/tags/")
    suspend fun getDramasTags(): PagingResponse<DramasTagsResponse>
}