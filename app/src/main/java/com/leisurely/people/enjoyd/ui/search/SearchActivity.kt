package com.leisurely.people.enjoyd.ui.search

import android.os.Bundle
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySearchBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.search.basic.SearchBasicLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchCategoryLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchHeaderLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchRecentLayout
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
    private lateinit var searchHeaderLayout: SearchHeaderLayout
    private lateinit var searchBasicLayout: SearchBasicLayout
    private lateinit var searchRecentLayout: SearchRecentLayout
    private lateinit var searchCategoryLayout: SearchCategoryLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        searchHeaderLayout = SearchHeaderLayout(this, layout_header, viewModel)
        searchBasicLayout = SearchBasicLayout(this, layout_basic, viewModel)
        searchRecentLayout = SearchRecentLayout(this, viewModel)
        searchCategoryLayout = SearchCategoryLayout(this, viewModel)
    }
}
