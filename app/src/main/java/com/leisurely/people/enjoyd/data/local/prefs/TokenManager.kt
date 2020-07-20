package com.leisurely.people.enjoyd.data.local.prefs

import android.app.Activity
import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.util.Constant
import com.leisurely.people.enjoyd.util.ext.getSharedPreference
import com.leisurely.people.enjoyd.util.ext.putSharedPreference

/**
 * 사용자 토큰 관리 클래스
 * SharedPreference 를 통해 사용자 토큰 값을 저장 및 관리
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
object TokenManager {

    fun setUserToken(context: Context, userToken: UserTokenResponse) {
        val userTokenJson = Gson().toJson(userToken)
        context.putSharedPreference(Constant.PREF_USER_TOKEN, userTokenJson)
    }

    fun getUserToken(context: Context): UserTokenResponse? {
        val prefData = context.getSharedPreference(Constant.PREF_USER_TOKEN, "")

        return if (prefData.isNotEmpty()) {
            Gson().fromJson(prefData, UserTokenResponse::class.java)
        } else {
            null
        }
    }

    fun deleteUserToken(context: Context) {
        context.getSharedPreferences(Constant.PREF_NAME, Activity.MODE_PRIVATE).edit(true) {
            remove(Constant.PREF_USER_TOKEN)
        }
    }

    fun getUserAccessToken(context: Context): String? {
        val userToken = getUserToken(context)
        return userToken?.accessToken
    }

    fun getUserRefreshToken(context: Context): String? {
        val userToken = getUserToken(context)
        return userToken?.refreshToken
    }

    fun getUserExpireDate(context: Context): String? {
        val userToken = getUserToken(context)
        return userToken?.expireDate
    }

    fun getUserIdx(context: Context): Int? {
        val userToken = getUserToken(context)
        return userToken?.pk
    }
}