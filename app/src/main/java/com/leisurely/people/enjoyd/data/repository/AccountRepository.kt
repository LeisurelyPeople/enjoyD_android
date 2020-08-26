package com.leisurely.people.enjoyd.data.repository

import android.content.Context
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.data.local.source.AccountLocalDataSource
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.remote.source.AccountRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

/**
 * 계정 관련 Repository 클래스
 * (로그인, 회원가입, 회원탈퇴, 갱신 등등..)
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class AccountRepository(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountLocalDataSource: AccountLocalDataSource
) {

    fun requestLogin(socialId: String): Completable {
        return accountRemoteDataSource.requestLogin(socialId).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.ignoreElement()
    }

    fun requestSignUp(signUpRequest: SignUpRequest): Completable {
        return accountRemoteDataSource.requestSignUp(signUpRequest).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.ignoreElement()
    }

    fun requestRefreshToken(refreshToken: String): Completable {
        return accountRemoteDataSource.requestRefreshToken(refreshToken).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.doOnError {
            accountLocalDataSource.deleteUserToken()
        }.ignoreElement()
    }
}