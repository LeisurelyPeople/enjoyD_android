package com.leisurely.people.enjoyd.data.remote.api

import com.google.gson.JsonObject
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.DeleteAccountsDramasDramaBookmarksRequest
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.*
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

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

    /** 드라마 정보 배너 API (/dramas/banner/)  */
    @GET("/dramas/banner/")
    fun getDramasBanner(): Single<DramaBannerGetResponse>

    /** 드라마정보리스트API API (/dramas) */
    @GET("/dramas/")
    fun getDramas(): Single<DramasGetResponse>

    /** 드라마정보 디테일 API (/dramas/{drama_info_slug}) */
    @GET("/dramas/{drama_info_slug}/")
    fun getDramasSlug(
        @Path("drama_info_slug") dramaInfoSlug: String
    ): Single<DramasSlugGetResponse>

    /** 드라마 정보 검색 API (/dramas/search) */
    @GET("/dramas/search/")
    fun getDramasSearch(
        @Query("search") search: String?,
        @Query("order") order: String = "avg_rating"
    ): Single<DramasSearchGetResponse>

    /** 태그 리스트 API (/dramas/tag/) */
    @GET("/dramas/tag/")
    fun getDramasTag(): Single<DramaTagGetResponse>

    /** 북마크 리스트 API (/accounts/dramas/drama/bookmarks/) */
    @GET("/accounts/dramas/drama/bookmarks/")
    fun getAccountsDramasDramaBookmarks(
    ): Single<AccountsDramasDramaBookmarksGetResponse>

    /** 북마크 다중 해제 API (/accounts/dramas/drama/bookmarks/) */
    @HTTP(method = "DELETE", path = "/accounts/dramas/drama/bookmarks/", hasBody = true)
    fun deleteAccountsDramasDramaBookmarks(
        @Body data: DeleteAccountsDramasDramaBookmarksRequest
    ): Single<JsonObject>

    /** 북마크 API (/accounts/dramas/drama/{drama_pk}/bookmark/) */
    @POST("/accounts/dramas/drama/{drama_pk}/bookmark/")
    fun postAccountsDramasDramaPkBookmark(
        @Path("drama_pk") dramaPk: Int
    ): Single<JsonObject>

    /** 북마크 해제 API (/accounts/dramas/drama/<int:drama_pk>/bookmark/)  */
    @DELETE("/accounts/dramas/drama/{drama_pk}/bookmark/")
    fun deleteAccountsDramasDramaPkBookmark(
        @Path("drama_pk") dramaPk: Int
    ): Single<JsonObject>
}