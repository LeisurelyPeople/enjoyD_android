package com.leisurely.people.enjoyd.ui.search.recent

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.layout_search_recent.view.*

/**
 * 최근 검색어 리스트 레이아웃
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchRecentLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    val activity: SearchActivity by lazy { activity }
    val layout: View by lazy { layout }
    val vm: SearchViewModel by lazy { vm }

    /** 최근 검색어 리스트 레이아웃을 초기화 한다. */
    init {
        val adapter = RecentListAdapter()
        layout.rv_recents.adapter = adapter
        vm.initRecents()
    }
}
