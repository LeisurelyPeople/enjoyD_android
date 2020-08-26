package com.leisurely.people.enjoyd.data.local.source

import android.content.Context
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse

/**
 * 계정 관련 Local DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.25
 */
class AccountLocalDataSource(private val context: Context) {

    fun saveUserTokenToSharedPrefs(userTokenResponse: UserTokenResponse) {
        TokenManager.setUserToken(context, userTokenResponse)
    }

    fun getUserTokenToSharedPrefs() = TokenManager.getUserToken(context)

    fun deleteUserToken() {
        TokenManager.deleteUserToken(context)
    }

}