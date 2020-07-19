package com.leisurely.people.enjoyd.ui.search.basic

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.layout_search_basic.view.*

/**
 * 처음 들어갈 시 보여주는 추천 리스트 레이아웃
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchBasicLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    val activity: SearchActivity by lazy { activity }
    val layout: View by lazy { layout }
    val vm: SearchViewModel by lazy { vm }

    /** 추천 리스트 레이아웃을 초기화 한다. */
    init {
        val adapter = BasicListAdapter()
        layout.rv_basics.adapter = adapter
    }

    /** 추천 리스트 레이아웃을 감춘다. */
    fun hideTimelineDataLayout() {
        layout.visibility = View.GONE
    }
}
