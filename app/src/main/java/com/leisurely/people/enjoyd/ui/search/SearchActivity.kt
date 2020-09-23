package com.leisurely.people.enjoyd.ui.search

import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySearchBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.search.basic.SearchBasicLayout
import com.leisurely.people.enjoyd.ui.search.header.SearchHeaderLayout
import com.leisurely.people.enjoyd.ui.search.recent.SearchRecentLayout
import com.leisurely.people.enjoyd.ui.search.searchResult.SearchResultLayout
import com.leisurely.people.enjoyd.util.ext.hideKeyboard
import kotlinx.android.synthetic.main.activity_search.*
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 검색 액티비티
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchActivity :
    BaseActivity<ActivitySearchBinding, SearchViewModel>(R.layout.activity_search) {
    override val viewModel: SearchViewModel by viewModel()
    private lateinit var searchHeaderLayout: SearchHeaderLayout
    private lateinit var searchBasicLayout: SearchBasicLayout
    private lateinit var searchRecentLayout: SearchRecentLayout

    //    private lateinit var searchAutoResultLayout: SearchHeaderLayout
    private lateinit var searchResultLayout: SearchResultLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchHeaderLayout = get { parametersOf(this, layout_header, viewModel) }
        searchBasicLayout = get { parametersOf(this, layout_basic, viewModel) }
        searchRecentLayout = get { parametersOf(this, layout_recent, viewModel) }
//        searchAutoResultLayout = get { parametersOf(this, layout_auto_result, viewModel) }
        searchResultLayout = get { parametersOf(this, layout_result, viewModel) }

        // searchResult 를 갱신했을 때는 검색이 완료된 상태이므로, 키보드를 가려준다.
        viewModel.searchResults.observe(this, Observer {
            hideKeyboard()
        })
    }
}
