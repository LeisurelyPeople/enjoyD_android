package com.leisurely.people.enjoyd.ui.search.searchResult

import android.view.View
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.util.TextDecoration.ColorUtil
import com.leisurely.people.enjoyd.util.TextDecoration.StyledText
import com.leisurely.people.enjoyd.util.TextDecoration.TextDecorationUtil
import com.leisurely.people.enjoyd.util.ext.styledNumber
import kotlinx.android.synthetic.main.layout_search_result.view.*

/**
 * 최근 검색어 리스트 레이아웃
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchResultLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    val activity: SearchActivity by lazy { activity }
    val layout: View by lazy { layout }
    val vm: SearchViewModel by lazy { vm }

    /** 최근 검색어 리스트 레이아웃을 초기화 한다. */
    init {
        val adapter = SearchResultAdapter()
        layout.rv_search_result.adapter = adapter

        vm.searchResults.observe(activity, Observer {
            // 평가하기 타이틀 텍스트 generate
            layout.tv_result_title.text = TextDecorationUtil.generateStyledText(
                listOf(
                    StyledText(
                        "입력한 검색 결과가 ",
                        color = ColorUtil.getColorStringToInt("#a1a1a1", "#ffffff")
                    ),
                    StyledText(
                        "${it.size.styledNumber()}건",
                        isBold = true,
                        color = ColorUtil.getColorStringToInt("#6e47ff", "#ffffff")
                    ),
                    StyledText(
                        " 있어요!",
                        color = ColorUtil.getColorStringToInt("#a1a1a1", "#ffffff")
                    )
                )
            )
        })
    }
}
