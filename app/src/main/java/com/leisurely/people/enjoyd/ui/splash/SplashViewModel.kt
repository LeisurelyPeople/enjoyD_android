package com.leisurely.people.enjoyd.ui.splash

import com.leisurely.people.enjoyd.data.repository.AccountRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.ext.applySchedulers
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import com.leisurely.people.enjoyd.util.observer.DisposableCompletableObserver

/**
 * 스플래쉬 화면 관련 ViewModel
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.26
 */
class SplashViewModel(private val accountRepository: AccountRepository) : BaseViewModel() {

    private val _startMain: LiveEvent<Unit> = LiveEvent()
    val startMain: LiveEvent<Unit> = _startMain

    private val _startLogin: LiveEvent<Unit> = LiveEvent()
    val startLogin: LiveEvent<Unit> = _startLogin

    fun checkUserToken() {
        val userToken = accountRepository.getUserToken() ?: kotlin.run {
            _startLogin.value = null
            return
        }

        /** access token 만료 되었을 경우 */
        if (userToken.expireDate.toLong() < System.currentTimeMillis()) {
            refreshUserToken(userToken.refreshToken)
        } else {
            _startMain.value = null
        }
    }

    private fun refreshUserToken(refreshToken: String) {
        accountRepository.requestRefreshToken(refreshToken)
            .applySchedulers()
            .subscribeWith(object : DisposableCompletableObserver() {
                /** 토큰 갱신 성공 시 메인 화면 이동 */
                override fun onComplete() {
                    _startMain.value = null
                }

                /** 토큰 갱신 실패 시 로그인 화면 이동 */
                override fun onError(e: Throwable) {
                    super.onError(e)
                    _startLogin.value = null
                }
            }).addDisposable()
    }
}