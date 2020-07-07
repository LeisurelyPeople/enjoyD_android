package com.leisurely.people.enjoyd.ui.login

import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

/**
 * 로그인 액티비티의 ViewModel
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */
class LoginViewModel : BaseViewModel() {

    private val _kakaoLoginClick: PublishSubject<Unit> = PublishSubject.create()

    val kakaoLoginClick: Subject<Unit> = _kakaoLoginClick

    init {
        _kakaoLoginClick
            .throttleFirst(4000L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .subscribe {

            }
            .addDisposable()
    }
}