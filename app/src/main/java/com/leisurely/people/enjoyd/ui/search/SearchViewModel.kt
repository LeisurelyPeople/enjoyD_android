package com.leisurely.people.enjoyd.ui.search

import android.text.Editable
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponseItem
import com.leisurely.people.enjoyd.model.search.RecentSearch
import com.leisurely.people.enjoyd.model.search.AutoResult
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.coroutine.CoroutineKey.SEARCH_CLICK_SEARCH_BTN
import com.leisurely.people.enjoyd.util.coroutine.SafeScope
import com.leisurely.people.enjoyd.util.ext.applySingleSchedulers
import com.leisurely.people.enjoyd.util.observer.DisposableSingleObserver
import com.leisurely.people.enjoyd.data.local.prefs.SearchWordsManager
import com.leisurely.people.enjoyd.util.time.TimePoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * 검색 액티비티 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchViewModel(private val dramaRepository: DramaRepository) : BaseViewModel() {
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

    /** 검색 결과 리스트 */
    private val _searchResults = MutableLiveData(listOf<DramasSearchResponseItem>())
    val searchResults: LiveData<List<DramasSearchResponseItem>> = _searchResults

    /** 검색 쿼리 텍스트 */
    private val _query = MutableLiveData("")
    val query: LiveData<String> = _query

    /** 검색을 위한 액션을 취했는지 안했는지를 감별하는 flag 값 (editText 버튼을 눌렀을 시 false) */
    private val _initClick = MutableLiveData<Boolean>(false)
    val initClick: MutableLiveData<Boolean> = _initClick

    /** 현재 검색어를 입력중인지 아닌지를 확인할 수 있는 flag 값 (검색버튼을 눌렀을 시 false) */
    private val _isTyping = MutableLiveData<Boolean>(false)
    val isTyping: MutableLiveData<Boolean> = _isTyping

    /**
     * 검색 쿼리 textWatcher
     * 참고 : https://steemit.com/kr/@jeonghamin/andoird-5-mvvm-edittext
     */
    fun queryAfterTextChanged(s: Editable) {
        Log.i(tag, "queryAfterTextChanged : s : $s")

//        // TODO 현재 _autoResults 를 사용하지 않으므로 주석처리함
//        if (s.isNotEmpty()) {
//            _autoResults.value = listOf(
//                AutoResult(true, "프로그램"),
//                AutoResult(false, "소녀의 세계"),
//                AutoResult(false, "소녀의 세계"),
//                AutoResult(false, "소녀의 세계"),
//                AutoResult(false, "소녀의 세계"),
//                AutoResult(true, "배우"),
//                AutoResult(false, "아이린"),
//                AutoResult(false, "태연"),
//                AutoResult(false, "혜리")
//            )
//        }

//        // api 호출
//        // recyclerview 에 반영
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

        _recents.value = SearchWordsManager.init()

        _autoResults.value = listOf()

        _searchResults.value = listOf()
    }

    /**
     * 검색 EditText 에 한 번이라도 포커스가 들어올 시 실행되는 리스너
     * 추천 레이아웃을 가린 후, 최근 검색어 레이아웃을 보여준다.
     */
    val queryOneFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        Log.i(tag, "$hasFocus")
        // 만약 포커스 된 적 있던 경우 실행하지 않는다.
        if (_initClick.value == false && hasFocus) {
            // 한번이라도 클릭했을 때 & 안 클릭했을 때 보여주는 화면이 다르므로 관련 flag 값 변경
            _initClick.value = true
            _isTyping.value = true
        }
    }

    /** 검색 에딧 텍스트를 클릭한 후, UI 이전에 해야할 내용들을 작업한다. */
    fun searchEditTextClick() {
        _isTyping.value = true
    }

    /** 검색 버튼을 클릭한 후, UI 이전에 해야할 내용들을 작업한다. */
    fun searchBtnClick() {
        if (_query.value.isNullOrEmpty()) {
            liveToastMessage.value = "최소 한 글자 이상 입력해주세요."
            return
        }
        _isTyping.value = false

        _recents.value = SearchWordsManager.put(
            RecentSearch(id = TimePoint.now.unixMillis, title = _query.value ?: " ")
        )

        SafeScope(logicName = SEARCH_CLICK_SEARCH_BTN).launch {
            Log.i(tag, "searchBtnClick")

            // 서버로부터 데이터를 받아온 후 키보드를 닫음 처리한다.
            dramaRepository.dramaInfoSearch(_query.value, "avg_rating")
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSearchResponse>() {
                    override fun onSuccess(searchDramas: DramasSearchResponse) {
                        _searchResults.value = searchDramas
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _searchResults.value = listOf()
                    }
                })
        }
    }

    /** 최근 검색어를 클릭한 후, UI 이전에 해야할 내용들을 작업한다. */
    fun searchRecentItemClick(recentText: String) {
        _query.value = recentText

        _isTyping.value = false

        // REMEMBER 기억하기 위해 일부로 Dispatchers.Main 을 언급함
        //          SafeScope 내에서 LiveData 를 사용하려면 Dispatchers 가 반드시 Main 이어야 함
        SafeScope(logicName = SEARCH_CLICK_SEARCH_BTN).launch(Dispatchers.Main) {
            Log.i(tag, "searchBtnClick")

            _recents.value = SearchWordsManager.put(
                RecentSearch(id = TimePoint.now.unixMillis, title = recentText)
            )

            // 서버로부터 데이터를 받아온 후 키보드를 닫음 처리한다.
            dramaRepository.dramaInfoSearch(recentText, "avg_rating")
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSearchResponse>() {
                    override fun onSuccess(searchDramas: DramasSearchResponse) {
                        _searchResults.value = searchDramas
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _searchResults.value = listOf()
                    }
                })
        }
    }

    /** 최근 검색어 아이템 삭제를 클릭한 후, UI 이전에 해야할 내용들을 작업한다. */
    fun searchRecentItemRemoveClick(recent: RecentSearch) {
        _recents.value = SearchWordsManager.delete(recent.title)
    }
}
