package com.leisurely.people.enjoyd.ui.search.layout

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel

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
}
