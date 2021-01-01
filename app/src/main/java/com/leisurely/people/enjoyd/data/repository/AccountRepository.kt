package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.local.source.AccountLocalDataSource
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserResponse
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.remote.source.AccountRemoteDataSource
import io.reactivex.Completable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

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

    fun getAccounts(): Flow<UserResponse> = flow {
        emit(accountRemoteDataSource.getAccounts())
    }.flowOn(Dispatchers.IO)

    fun requestLogin(socialId: String): Completable {
        return accountRemoteDataSource.postAccountsSignIn(socialId).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.ignoreElement()
    }

    fun requestSignUp(signUpRequest: SignUpRequest): Completable {
        return accountRemoteDataSource.postAccountsSignUp(signUpRequest).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.ignoreElement()
    }

    fun requestRefreshToken(refreshToken: String): Completable {
        return accountRemoteDataSource.postAccountsRefreshToken(refreshToken).doOnSuccess {
            accountLocalDataSource.saveUserTokenToSharedPrefs(it)
        }.doOnError {
            accountLocalDataSource.deleteUserToken()
        }.ignoreElement()
    }

    fun getUserToken(): UserTokenResponse? {
        return accountLocalDataSource.getUserTokenToSharedPrefs()
    }
}