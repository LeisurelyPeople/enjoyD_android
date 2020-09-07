package com.leisurely.people.enjoyd.data.remote.interceptor

import com.leisurely.people.enjoyd.data.local.prefs.TokenManager
import com.leisurely.people.enjoyd.ui.base.EnjoyDApplication
import com.leisurely.people.enjoyd.util.coroutine.isUnitTest
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 네트워크 통신 관련 인터셉터 클래스
 * 헤더에 토큰 및 데이터 담는 용도
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

        // TODO accessToken 이 필요없는 경우에 대해 조건문 임시방편으로 넣음
        val accessToken = if (!isUnitTest)
            TokenManager.getUserAccessToken(EnjoyDApplication.instance)
        else
            ""

        accessToken?.let {
            /** API 헤더 토큰 추가 */
            request.addHeader("Authorization", "Bearer $it")
        }
        request.addHeader("Content-Type", "application/json")

        return chain.proceed(request.build())
    }
}