package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.remote.drama.DramaInfoPkResponse
import com.leisurely.people.enjoyd.data.remote.drama.DramaInfoResponse
import com.leisurely.people.enjoyd.data.remote.drama.SearchDrama
import io.reactivex.Single
import retrofit2.http.*

/**
 * EnjoyD Api 들을 관리하는 인터페이스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

interface APIService {

    @POST("accounts/sign-up/")
    fun requestSignUp(@Body data: SignUpRequest): Single<UserTokenResponse>

    @POST("accounts/sign-in/")
    fun requestLogin(@Body data: HashMap<String, String>): Single<UserTokenResponse>

    @POST("accounts/refresh/")
    fun requestRefreshToken(@Body data: HashMap<String, String>): Single<UserTokenResponse>

    /** 간략한 드라마 정보 리스트 API (/dramas/info) */
    @GET("/dramas/info/")
    fun dramasInfo(): Single<DramaInfoResponse>

    /** 자세한 드라마 정보 리스트 API (/dramas/info/{pk}) */
    @GET("/dramas/info/{pk}/")
    fun dramasInfoPk(@Path("pk") pk: String): Single<DramaInfoPkResponse>

    /** 드라마 정보 검색 API (/dramas/info/search) */
    @GET("/dramas/info/search/")
    fun dramasInfoSearch(
        @Query("search") search: String?,
        @Query("order") order: String = "avg_rating"
    ): Single<List<SearchDrama>>
}
