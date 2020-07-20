package com.leisurely.people.enjoyd.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.data.local.RecentSearch
import com.leisurely.people.enjoyd.data.remote.search.AutoResult
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
    val basics: LiveData<List<String>> = _basics

    /** 최근 검색어 리스트 */
    private val _recents = MutableLiveData(listOf<RecentSearch>())
    val recents: LiveData<List<RecentSearch>> = _recents

    /** 카테고리별 이름 리스트 (프로그램 / 배우) */
    private val _autoResults = MutableLiveData(listOf<AutoResult>())
    val autoResults: LiveData<List<AutoResult>> = _autoResults

    /** 검색 쿼리 텍스트 */
    val query = ObservableField<String>()

    /** 검색을 위한 액션을 취했는지 안했는지를 감별하는 flag 값 (editText 버튼을 눌렀을 시 false) */
    val initClick = ObservableField<Boolean>().apply { set(false) }

    /**
     * 검색 쿼리 textWatcher
     * 참고 : https://steemit.com/kr/@jeonghamin/andoird-5-mvvm-edittext
     */
    val queryWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            Log.i(tag, "beforeTextChanged : s : $s, start : $start, count : $count, after : $after")
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            Log.i(tag, "onTextChanged : s : $s, start : $start, count : $count")
        }

        override fun afterTextChanged(s: Editable) {
            Log.i(tag, "afterTextChanged : s : $s")

            // TODO 현재 LiveData인 _recents / _autoResults 가 정상적으로 적용되지 않음. 아키텍처에 대해 확인 필요
            // 검색 내용값이 비어있다면 최신순 리스트를 보여주고, 아니라면 자동완성 리스트를 보여준다.
            if (s.isEmpty()) {
                _recents.value = listOf()
            } else
                _autoResults.value = listOf(
                    AutoResult(true, "프로그램"),
                    AutoResult(false, "소녀의 세계"),
                    AutoResult(false, "소녀의 세계"),
                    AutoResult(false, "소녀의 세계"),
                    AutoResult(false, "소녀의 세계"),
                    AutoResult(true, "배우"),
                    AutoResult(false, "아이린"),
                    AutoResult(false, "태연"),
                    AutoResult(false, "혜리")
                )

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

        _recents.value = listOf(
            RecentSearch(0, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(1, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(2, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(3, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(4, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(5, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(6, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(7, "방금 검색바 클릭했을 때 세계"),
            RecentSearch(8, "방금 검색바 클릭했을 때 세계")
        )

        _autoResults.value = listOf()
    }
}
