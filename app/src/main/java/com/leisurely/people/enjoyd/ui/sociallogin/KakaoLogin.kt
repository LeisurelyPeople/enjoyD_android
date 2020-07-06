package com.leisurely.people.enjoyd.ui.sociallogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.leisurely.people.enjoyd.ui.base.BaseSocialLogin
import com.leisurely.people.enjoyd.ui.base.OnLoginFail
import com.leisurely.people.enjoyd.ui.base.OnLoginSuccess

/**
 * 카카오 로그인 관리 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */

class KakaoLogin<T>(
    activity: AppCompatActivity,
    onLoginSuccess: OnLoginSuccess<T>? = null,
    onLoginFail: OnLoginFail? = null
): BaseSocialLogin<T>(activity, onLoginSuccess, onLoginFail) {

    private var sessionCallback: SessionCallback? = null

    private val session by lazy {
        Session.getCurrentSession()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)
    }

    override fun login() {
        sessionCallback = SessionCallback()
        session.addCallback(sessionCallback)
        session.open(AuthType.KAKAO_TALK, activity)
    }

    override fun logout() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {

            }
        })
    }

    fun onDestroy() {
        sessionCallback?.let {
            Session.getCurrentSession().removeCallback(sessionCallback)
        }
    }

    private inner class SessionCallback : ISessionCallback {

        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null && !exception.isCancledOperation) {
                callbackAsFail(exception)
            }
        }

        override fun onSessionOpened() {
            requestProfile()
        }
    }

    private fun requestProfile() {
        val requestOptions = arrayListOf("kakao_account.email")

        UserManagement.getInstance().me(requestOptions, object : MeV2ResponseCallback() {
            override fun onSessionClosed(errorResult: ErrorResult) {

            }

            override fun onSuccess(result: MeV2Response) {
                // 프로필 요청 성공
            }

            override fun onFailure(errorResult: ErrorResult) {
                super.onFailure(errorResult)
                callbackAsFail(errorResult.exception)
            }
        })
    }

}