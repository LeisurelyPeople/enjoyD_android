package com.leisurely.people.enjoyd.ui.search.layout

import android.util.Log
import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.layout_search_header.view.*

/**
 * 검색화면 헤더 레이아웃 (검색 바)
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchHeaderLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    private val searchHeaderEditText: SearchHeaderEditText by lazy {
        SearchHeaderEditText(activity).apply { init(activity, vm) }
    }

    /**
     * 검색 EditText 에 한 번이라도 포커스가 들어올 시 실행되는 리스너
     * 추천 레이아웃을 가린 후, 최근 검색어 레이아웃을 보여준다.
     */
    private val queryOneFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        Log.i(tag, "$hasFocus")
        if (hasFocus) {
            activity.searchBasicLayout?.hideTimelineDataLayout()
            activity.initSearchRecentLayout()

            // 만약 한번이라도 실행되면 바로 없애준다.
            layout.et_query.onFocusChangeListener = null
        }
    }

    init {
        layout.et_query.onFocusChangeListener = queryOneFocusChangeListener
    }

}
