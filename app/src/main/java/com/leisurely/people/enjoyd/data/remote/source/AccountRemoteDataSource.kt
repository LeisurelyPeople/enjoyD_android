package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.AuthService
import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserResponse
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import io.reactivex.Single

/**
 * 계정 관련 Remote DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class AccountRemoteDataSource(
    private val authService: AuthService,
    private val enjoyDService: EnjoyDService
) {
    suspend fun getAccounts(): UserResponse = enjoyDService.getAccounts()

    fun postAccountsSignIn(socialId: String): Single<UserTokenResponse> {
        return authService.postAccountsSignIn(hashMapOf("social_id" to socialId))
    }

    fun postAccountsSignUp(signUpRequest: SignUpRequest): Single<UserTokenResponse> {
        return authService.postAccountsSignUp(signUpRequest)
    }

    fun postAccountsRefreshToken(refreshToken: String): Single<UserTokenResponse> {
        return authService.postAccountsRefresh(hashMapOf("refresh_token" to refreshToken))
    }
}