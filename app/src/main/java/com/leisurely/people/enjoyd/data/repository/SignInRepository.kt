package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.remote.source.SignInRemoteDataSource
import io.reactivex.Single

/**
 * 로그인 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class SignInRepository(private val signInRemoteDataSource: SignInRemoteDataSource) {

    fun reqeustSignIn(socialId: String): Single<UserTokenResponse> {
        return signInRemoteDataSource.requestLogin(socialId)
    }
}