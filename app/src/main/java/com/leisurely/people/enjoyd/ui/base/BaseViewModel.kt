package com.leisurely.people.enjoyd.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leisurely.people.enjoyd.util.Constant
import com.leisurely.people.enjoyd.util.NotNullMutableLiveData
import com.leisurely.people.enjoyd.util.coroutine.CoroutineUtil
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.HttpException
import java.net.ConnectException

/**
 * ViewModel Base 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseViewModel : ViewModel() {

    private val _liveLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val liveLoading: LiveData<Boolean> = _liveLoading

    private val _liveToastMessage: LiveEvent<String> = LiveEvent()
    var liveToastMessage = _liveToastMessage

    private val _startLogout: LiveEvent<Unit> = LiveEvent()
    val startLogout: LiveEvent<Unit> = _startLogout

    private val compositeDisposable = CompositeDisposable()

    fun handleException(throwable: Throwable) {
        if (_liveLoading.value!!) hideLoading()
        when (throwable) {
            is HttpException -> {
                /** 토큰 값이 만료 되었을 경우 */
                if (throwable.code() == 401) {
                    _startLogout.value = null // 로그아웃 처리
                }
            }
            is ConnectException -> {
                /** 네트워크 연결 실패 시 */
                if (throwable.message == Constant.ERROR_NETWORK_CONNECTION_FAIL) {
                    showToast("네트워크 상태를 확인해주세요.")
                }
            }
        }
    }

    /**
     * ViewModel 기본 예외 처리기
     * ViewModel 내에서는 이 exceptionHandler 를 사용한다.
     */
    fun vmDefaultExceptionHandler(logicName: String) = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()

//    if (!isTestable && logicName != UNKNOWN_LOGIC)
//        FCrashlytics.logException(CoroutineUtil.generateThrowable(logicName, throwable))
//    else
//        FCrashlytics.logException(throwable)
        CoroutineUtil.clear(logicName)
    }

    fun showLoading() {
        _liveLoading.value = true
    }

    fun hideLoading() {
        _liveLoading.value = false
    }

    fun showToast(message: String) {
        _liveToastMessage.value = message
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
