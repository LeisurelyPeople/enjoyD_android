package com.leisurely.people.enjoyd.ui.base

import androidx.lifecycle.ViewModel
import com.leisurely.people.enjoyd.util.NotNullMutableLiveData
import com.leisurely.people.enjoyd.util.coroutine.CoroutineUtil
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * ViewModel Base 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseViewModel : ViewModel() {

    private var liveLoading: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    var liveToastMessage = LiveEvent<String>()

    private val compositeDisposable = CompositeDisposable()

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
        liveLoading.value = true
    }

    fun hideLoading() {
        liveLoading.value = false
    }

    fun showToast(message: String) {
        liveToastMessage.value = message
    }

    protected fun Disposable.addDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
