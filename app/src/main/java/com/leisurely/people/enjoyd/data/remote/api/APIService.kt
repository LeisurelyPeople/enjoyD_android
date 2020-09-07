package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoPkResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramaInfoSearchResponse
import retrofit2.http.*

/**
 * EnjoyD Api 들을 관리하는 인터페이스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

interface APIService {

    @POST("accounts/sign-up/")
    fun postAccountsSignUp(@Body data: SignUpRequest): Single<UserTokenResponse>

    @POST("accounts/sign-in/")
    fun postAccountsSignIn(@Body data: HashMap<String, String>): Single<UserTokenResponse>

    @POST("accounts/refresh/")
    fun postAccountsRefresh(@Body data: HashMap<String, String>): Single<UserTokenResponse>

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
