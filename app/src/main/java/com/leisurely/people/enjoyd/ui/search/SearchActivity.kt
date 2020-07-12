package com.leisurely.people.enjoyd.ui.search

import android.os.Bundle
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySearchBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.search.layout.SearchCategoryLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchDefaultLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchHeaderLayout
import com.leisurely.people.enjoyd.ui.search.layout.SearchRecentLayout
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
    private val searchHeaderLayout by lazy { SearchHeaderLayout(this, viewModel) }
    private val searchDefaultLayout by lazy { SearchDefaultLayout(this, viewModel) }
    private val searchRecentLayout by lazy { SearchRecentLayout(this, viewModel) }
    private val searchCategoryLayout by lazy { SearchCategoryLayout(this, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
