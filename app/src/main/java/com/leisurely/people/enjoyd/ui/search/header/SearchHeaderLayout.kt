package com.leisurely.people.enjoyd.ui.search.header

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.util.ext.hideKeyboard
import com.leisurely.people.enjoyd.util.ext.setOnSingleClickListener
import kotlinx.android.synthetic.main.layout_search_header.view.*

/**
 * 검색화면 헤더 레이아웃 (검색 바)
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchHeaderLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    init {
        layout.apply {
            btn_back.setOnSingleClickListener {
                layout.hideKeyboard()
            }
        }
    }

}
