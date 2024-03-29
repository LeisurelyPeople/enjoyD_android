package com.leisurely.people.enjoyd.data.remote.interceptor

import android.content.Context
import com.google.gson.Gson
import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.util.Constant.Companion.dummyUserAccessToken
import com.leisurely.people.enjoyd.util.Constant.Companion.isUnitTest
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

        // 만약 유닛 테스트에서 요청한 작업이라면 더미 유저의 엑세스 토큰을 헤더에 넣어준다.
        if (isUnitTest)
            return chain.proceed(
                originRequest.newBuilder().addHeaders(dummyUserAccessToken).build()
            )

        // 유저 토큰을 가져온다. null 이라면 그냥 넘어간다.
        val userToken = TokenManager.getUserToken(context) ?: kotlin.run {
            return chain.proceed(originRequest.newBuilder().build())
        }

        val accessTokenExpiredDate = userToken.expireDate
        val accessToken = userToken.accessToken
        val refreshToken = userToken.refreshToken

        return if (accessTokenExpiredDate > System.currentTimeMillis()) {
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