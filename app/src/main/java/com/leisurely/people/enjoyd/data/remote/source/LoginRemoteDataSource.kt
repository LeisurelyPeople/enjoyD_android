package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.APIService
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import io.reactivex.Single

/**
 * 로그인 데이터 소스 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class LoginRemoteDataSource(private val apiService: APIService) {

    fun requestLogin(socialId: String): Single<UserTokenResponse> {
        return apiService.requestLogin(hashMapOf("social_id" to socialId))
    }
}