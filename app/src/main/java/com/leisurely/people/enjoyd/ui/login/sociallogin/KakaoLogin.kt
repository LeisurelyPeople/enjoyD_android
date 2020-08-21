package com.leisurely.people.enjoyd.ui.login.sociallogin

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
import com.kakao.usermgmt.response.model.UserAccount
import com.kakao.util.exception.KakaoException
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.ui.base.BaseSocialLogin
import com.leisurely.people.enjoyd.ui.base.OnLoginFail
import com.leisurely.people.enjoyd.ui.base.OnLoginSuccess
import com.leisurely.people.enjoyd.ui.login.model.SocialLogin

/**
 * 카카오 로그인 관리 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */

class KakaoLogin(
    activity: AppCompatActivity,
    onLoginSuccess: OnLoginSuccess<SocialLogin>? = null,
    onLoginFail: OnLoginFail? = null
) : BaseSocialLogin<SocialLogin>(activity, onLoginSuccess, onLoginFail) {

    private var sessionCallback: SessionCallback = SessionCallback()

    private val session by lazy {
        Session.getCurrentSession()
    }

    /** LoginActivity onActivityResult에서 받은 값을 카카오 Session 클래스로 값을 넘겨주기 위한 메소드 */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)
    }

    /** 카카오 로그인을 처리하기 위한 메소드  */
    override fun login() {
        session.addCallback(sessionCallback)
        session.open(AuthType.KAKAO_TALK, activity)
    }

    /** 카카오 로그아웃을 처리하기 위한 메소드 */
    override fun logout() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {

            }
        })
    }

    /**
     * sessionCallback 이 중첩으로 들어가는 걸 막기 위한 메소드
     * 중첩으로 들어가게 되면 SessionCallback 이 여러번 호출되는 이슈가 있음
     *  */
    fun onDestroy() {
        Session.getCurrentSession().removeCallback(sessionCallback)
    }

    /** 로그인 세션을 성공, 실패 여부를 받기 위한 클래스 */
    private inner class SessionCallback : ISessionCallback {

        /** 세션 여는데 실패 시 호출되는 메소드 */
        override fun onSessionOpenFailed(exception: KakaoException?) {
            if (exception != null && !exception.isCancledOperation) {
                callbackAsFail(exception) // 실패 시 실패 값을 액티비티로 전달
            }
        }

        /** 세션 여는데 성공 시 호출되는 메소드 */
        override fun onSessionOpened() {
            requestProfile() // 성공 시 사용자 프로필 정보 요청 메소드 호출
        }
    }

    /** 사용자 프로필을 요청하기 위한 메소드 */
    private fun requestProfile() {
        UserManagement.getInstance().me(object : MeV2ResponseCallback() {

            /** 세션이 닫혔을때 호출되는 메소드 */
            override fun onSessionClosed(errorResult: ErrorResult) {

            }

            /** 프로필 정보 요청 성공 시 호출 되는 메소드 */
            override fun onSuccess(result: MeV2Response) {
                val userAccount: UserAccount? = result.kakaoAccount
                userAccount?.let {
                    val gender = when (it.gender?.name) {
                        "female" -> 1
                        "male" -> 0
                        else -> null
                    }
                    callbackAsSuccess(SocialLogin(result.id, it.profile?.nickname, gender))
                    return
                } ?: kotlin.run {
                    callbackAsFail(Exception())
                }
            }

            /** 프로필 정보 요청 실패 시 호출 되는 메소드 */
            override fun onFailure(errorResult: ErrorResult) {
                super.onFailure(errorResult)
                callbackAsFail(errorResult.exception)
            }
        })
    }
}