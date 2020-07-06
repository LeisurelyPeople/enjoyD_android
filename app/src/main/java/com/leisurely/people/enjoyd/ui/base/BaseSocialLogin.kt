package com.leisurely.people.enjoyd.ui.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * 소셜 로그인 Base 클래스
 * login, logout, data callback
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.06
 */

typealias OnLoginSuccess<T> = (T) -> Unit
typealias OnLoginFail = (Throwable) -> Unit

abstract class BaseSocialLogin<T>(
    val activity: AppCompatActivity,
    private val onSuccess: OnLoginSuccess<T>?,
    private val onError: OnLoginFail?
) {

    abstract fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)

    abstract fun login()

    abstract fun logout()

    protected fun callbackAsFail(exception: Exception) {
        onError?.invoke(exception)
    }

    protected fun callbackAsSuccess(result: T) {
        onSuccess?.invoke(result)
    }
}