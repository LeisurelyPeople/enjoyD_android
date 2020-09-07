package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.data.remote.api.APIService
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * 계정 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class AccountRemoteDataSource(private val apiService: APIService) {

    fun requestLogin(socialId: String): Single<UserTokenResponse> {
        return apiService.requestLogin(hashMapOf("social_id" to socialId))
    }

    fun requestSignUp(signUpRequest: SignUpRequest): Single<UserTokenResponse> {
        return apiService.requestSignUp(signUpRequest)
    }

    fun requestRefreshToken(refreshToken: String): Single<UserTokenResponse> {
        return apiService.requestRefreshToken(hashMapOf("refresh_token" to refreshToken))
    }
}