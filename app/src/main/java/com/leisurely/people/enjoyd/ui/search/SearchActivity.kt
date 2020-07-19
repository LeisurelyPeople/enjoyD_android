package com.leisurely.people.enjoyd.ui.search

import android.os.Bundle
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySearchBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.search.basic.SearchBasicLayout
import com.leisurely.people.enjoyd.ui.search.autoResult.SearchAutoResultLayout
import com.leisurely.people.enjoyd.ui.search.header.SearchHeaderLayout
import com.leisurely.people.enjoyd.ui.search.recent.SearchRecentLayout
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 검색 액티비티
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    public override val viewModel: SearchViewModel by viewModel()
    var searchHeaderLayout: SearchHeaderLayout? = null
    var searchBasicLayout: SearchBasicLayout? = null
    var searchRecentLayout: SearchRecentLayout? = null
    var searchAutoResultLayout: SearchAutoResultLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchHeaderLayout =
            SearchHeaderLayout(
                this,
                layout_header,
                viewModel
            )
        searchBasicLayout = SearchBasicLayout(this, layout_basic, viewModel)
    }

    /** 최근 검색 레이아웃을 세팅한다. */
    fun initSearchRecentLayout() {
        if (searchRecentLayout == null)
            searchRecentLayout = SearchRecentLayout(this, layout_recent.inflate(), viewModel)
    }

    /** 자동 검색완성 레이아웃을 세팅한다. */
    fun initAutoResultLayout() {
        if (searchAutoResultLayout == null)
            searchAutoResultLayout =
                SearchAutoResultLayout(this, layout_auto_result.inflate(), viewModel)
    }
}
