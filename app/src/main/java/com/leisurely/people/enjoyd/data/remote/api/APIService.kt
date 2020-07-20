package com.leisurely.people.enjoyd.data.remote.api

import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * EnjoyD Api 들을 관리하는 인터페이스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

interface APIService {

    @POST("accounts/sign-in/")
    fun requestSignIn(@Body data: HashMap<String, String>): Single<UserTokenResponse>
}
