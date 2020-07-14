package com.leisurely.people.enjoyd.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.time.TimePoint


/**
 * 검색 액티비티 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchViewModel : BaseViewModel() {
    val tag = this.javaClass.canonicalName

    /** 처음 들어갈 시 보여주는 추천 리스트 */
    private val _basics = MutableLiveData(listOf<String>())
    val basics: LiveData<List<String>>
        get() = _basics

    /** 최근 검색어 리스트 */
    private val _recents = MutableLiveData(listOf<String>())
    val recents: LiveData<List<String>>
        get() = _recents

    /** 카테고리별 이름 리스트 (프로그램 / 배우) */
    private val _categories = MutableLiveData(listOf<String>())
    val categories: LiveData<List<String>>
        get() = _categories

    /** 검색 쿼리 텍스트 */
    val query = ObservableField<String>()

    /**
     * 검색 쿼리 textWatcher
     * 참고 : https://steemit.com/kr/@jeonghamin/andoird-5-mvvm-edittext
     */
    val queryWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            Log.i(tag, "s : $s, start : $start, count : $count, after : $after")
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            Log.i(tag, "s : $s, start : $start, count : $count")
        }

        override fun afterTextChanged(s: Editable) {
            Log.i(tag, "s : $s")
            // api 호출
            // recyclerview 에 반영
        }
    }

    init {
        _basics.value = listOf(
            "NEW 업데이트작",
            "${TimePoint.today.month}월 인기 드라마",
            "최신 업데이트작",
            "평점 높은 작품",
            "${TimePoint.today.month}월 인기 에피소드",
            "#캠퍼스 로맨스",
            "리뷰 TOP 화제 드라마"
        )
    }
}
