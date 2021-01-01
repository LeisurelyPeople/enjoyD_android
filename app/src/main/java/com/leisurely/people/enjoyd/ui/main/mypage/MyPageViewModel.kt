package com.leisurely.people.enjoyd.ui.main.mypage

import android.util.Log
import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.response.UserResponse
import com.leisurely.people.enjoyd.data.repository.AccountRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

/**
 * 마이페이지 탭 관련 ViewModel 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class MyPageViewModel(private val accountRepository: AccountRepository) : BaseViewModel() {

    /** 프로필 수정 화면으로 이동하기위한 LiveData */
    private val _startProfileEditPage: LiveEvent<Unit> = LiveEvent()
    val startProfileEditPage: LiveEvent<Unit> = _startProfileEditPage

    /** 사용자 정보를 가지고 있는 LiveData */
    val userLiveData: LiveData<UserResponse> = accountRepository.getAccounts()
        .catch { handleException(throwable = it) }.asLiveData()

    private val _totalCount: MutableLiveData<Int> = MutableLiveData(0)
    val totalCount: LiveData<Int> = _totalCount

    fun goToProfileEditPage() {
        _startProfileEditPage.value = null
    }

    fun setDramasTotalCount(totalCount: Int) {
        _totalCount.value = totalCount
    }
}