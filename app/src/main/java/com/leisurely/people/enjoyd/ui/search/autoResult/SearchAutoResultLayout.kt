package com.leisurely.people.enjoyd.ui.search.autoResult

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import kotlinx.android.synthetic.main.layout_search_auto_result.view.*

/**
 * 자동 검색완성 리스트 레이아웃 (프로그램 / 배우)
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
@Deprecated("기획적으로 확정이 난 내용이 아니어서 이 화면은 사용하지 않음")
class SearchAutoResultLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    val activity: SearchActivity by lazy { activity }
    val layout: View by lazy { layout }
    val vm: SearchViewModel by lazy { vm }

    /**  레이아웃을 초기화 한다. */
    init {
        val adapter = AutoResultAdapter()
        layout.rv_auto_result.adapter = adapter
    }
}
