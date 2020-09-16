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
 * Auth(인증) 관련된 API 들을 관리하는 인터페이스
 * 해당 인터페이스에 담는 API 들을 헤더에 토큰을 담지 않는다.
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

interface AuthService {

    @POST("accounts/sign-up/")
    fun postAccountsSignUp(@Body data: SignUpRequest): Single<UserTokenResponse>

    @POST("accounts/sign-in/")
    fun postAccountsSignIn(@Body data: HashMap<String, String>): Single<UserTokenResponse>

    @POST("accounts/refresh/")
    fun postAccountsRefresh(@Body data: HashMap<String, String>): Single<UserTokenResponse>
}
