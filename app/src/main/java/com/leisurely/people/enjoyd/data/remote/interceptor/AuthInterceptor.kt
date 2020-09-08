package com.leisurely.people.enjoyd.data.remote.interceptor

import android.content.Context
import com.google.gson.Gson
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import okhttp3.Interceptor
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONObject

/**
 * 네트워크 통신 관련 인터셉터 클래스
 * 헤더에 토큰 및 토큰 갱신 처리 용도
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class AuthInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val userToken = TokenManager.getUserToken(context) ?: kotlin.run {
            return chain.proceed(originRequest.newBuilder().build())
        }

        val accessTokenExpiredDate = userToken.expireDate
        val accessToken = userToken.accessToken
        val refreshToken = userToken.refreshToken

        return if (accessTokenExpiredDate.toLong() > System.currentTimeMillis()) {
            chain.proceed(originRequest.newBuilder().addHeaders(accessToken).build())
        } else {
            val body = JSONObject().put("refresh_token", refreshToken).toString()
                .toRequestBody("application/json".toMediaTypeOrNull())

            val requestBody = Request.Builder()
                .url("http://3.35.87.123/accounts/refresh/")
                .post(body)
                .build()

            val serverResponse = OkHttpClient().newCall(requestBody).execute()

            if (serverResponse.isSuccessful) {
                serverResponse.body?.string()?.let {
                    val userTokenResponse = Gson().fromJson(it, UserTokenResponse::class.java)
                    TokenManager.setUserToken(context, userTokenResponse)
                    chain.proceed(
                        originRequest.newBuilder().addHeaders(userTokenResponse.accessToken).build()
                    )
                } ?: chain.proceed(originRequest)
            } else {
                chain.proceed(originRequest)
            }
        }
    }

    private fun Request.Builder.addHeaders(token: String) =
        this.apply { header("Authorization", "Bearer $token") }
}