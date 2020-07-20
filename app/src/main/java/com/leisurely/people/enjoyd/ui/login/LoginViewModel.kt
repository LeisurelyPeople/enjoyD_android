package com.leisurely.people.enjoyd.ui.login

import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.repository.SignInRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.util.ext.applySingleSchedulers
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import retrofit2.HttpException
import java.util.concurrent.TimeUnit

/**
 * 로그인 액티비티의 ViewModel
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */
class LoginViewModel(
    private val kakaoLogin: KakaoLogin,
    private val signInRepository: SignInRepository
) : BaseViewModel() {

    /** 로그인 클릭 Subject */
    private val _kakaoLoginClick: PublishSubject<Unit> = PublishSubject.create()
    val kakaoLoginClick: Subject<Unit> = _kakaoLoginClick

    private val _startMain = LiveEvent<UserTokenResponse>()
    val startMain = _startMain

    init {
        _kakaoLoginClick
            .throttleFirst(4000L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                kakaoLogin.login()
            }
            .addDisposable()
    }

    fun requestLogin(signUpRequest: SignUpRequest) {
        signInRepository.reqeustSignIn(signUpRequest.socialId.toString())
            .applySingleSchedulers()
            .subscribe({ userTokenResponse ->
                _startMain.value = userTokenResponse
            }, {
                /** 400 에러 인조이디 회원유저가 아닌 경우*/
                if ((it as? HttpException)?.code() == 400) {
                    // TODO 회원가입 화면 전환
                } else {
                    showToast("잠시 후 다시 시도해주세요.")
                }
            }).addDisposable()
    }
}