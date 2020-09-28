package com.leisurely.people.enjoyd.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent

/**
 * 상세 화면의 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.28
 */
class DetailViewModel : BaseViewModel() {
    /** 뒤로가기 버튼을 동작시키기 위한 LiveData */
    private val _startBackScreen: LiveEvent<Unit> = LiveEvent()
    val startBackScreen: LiveEvent<Unit> = _startBackScreen

    // 상단 actionBar 제목
    private val _title: MutableLiveData<String> = MutableLiveData()
    var title: LiveData<String> = _title

    /** 뒤로가기 버튼을 동작시키기 위한 메소드 */
    fun onClickBackScreen() {
        _startBackScreen.value = null
    }
}
