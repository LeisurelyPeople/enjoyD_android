package com.leisurely.people.enjoyd.ui.login

import com.leisurely.people.enjoyd.data.remote.data.response.UserTokenResponse
import com.leisurely.people.enjoyd.data.repository.AccountRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.ui.login.model.SocialLogin
import com.leisurely.people.enjoyd.ui.login.sociallogin.KakaoLogin
import com.leisurely.people.enjoyd.util.ext.applySchedulers
import com.leisurely.people.enjoyd.util.ext.applySingleSchedulers
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import com.leisurely.people.enjoyd.util.observer.DisposableCompletableObserver
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
    private val accountRepository: AccountRepository
) : BaseViewModel() {

    /** 로그인 클릭 Subject */
    private val _kakaoLoginClick: PublishSubject<Unit> = PublishSubject.create()
    val kakaoLoginClick: Subject<Unit> = _kakaoLoginClick

    /** main activity 이동하기 위한 LiveData */
    private val _startMain = LiveEvent<Unit>()
    val startMain: LiveEvent<Unit> = _startMain

    /** 회원가입 화면으로 이동하기 위한 LiveData */
    private val _startOnBoarding = LiveEvent<SocialLogin>()
    val startOnBoarding: LiveEvent<SocialLogin> = _startOnBoarding

    /** LoginActivity 재실행 하기 위한 LiveData */
    private val _reStartLogin = LiveEvent<Unit>()
    val reStartLogin: LiveEvent<Unit> = _reStartLogin

    init {
        _kakaoLoginClick
            .throttleFirst(4000L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {
                kakaoLogin.login()
            }
            .addDisposable()
    }

    fun requestLogin(socialLogin: SocialLogin) {
        if (socialLogin.socialId == null) return

        accountRepository.requestLogin(socialLogin.socialId.toString())
            .applySchedulers()
            .subscribeWith(object : DisposableCompletableObserver() {
                override fun onComplete() {
                    _startMain.value = null
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    if ((e as? HttpException)?.code() == 400) {
                        _startOnBoarding.value = socialLogin
                    } else {
                        _reStartLogin.value = null
                    }
                }
            }).addDisposable()
    }
}