package com.leisurely.people.enjoyd.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leisurely.people.enjoyd.util.NotNullMutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * ViewModel Base 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseViewModel : ViewModel() {

    private var liveLoading: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    var liveToastMessage: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

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