package com.leisurely.people.enjoyd.util.observer

import android.widget.Toast
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.ui.base.EnjoyDApplication
import com.leisurely.people.enjoyd.util.ext.isNetworkConnected
import io.reactivex.observers.DisposableSingleObserver
import retrofit2.HttpException

/**
 * RxJava Single 관련 에러 핸들링 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.25
 */
abstract class DisposableSingleObserver<T> : DisposableSingleObserver<T>() {
    override fun onError(e: Throwable) {
        with(EnjoyDApplication.instance) {
            if ((e as? HttpException)?.code() == 401) {
                logout()
            } else {
                if (!isNetworkConnected()) {
                    Toast.makeText(
                        this,
                        getString(R.string.network_disconnected_notice),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}