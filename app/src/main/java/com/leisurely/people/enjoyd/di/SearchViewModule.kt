package com.leisurely.people.enjoyd.di

import android.view.View
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.ui.search.basic.SearchBasicLayout
import com.leisurely.people.enjoyd.ui.search.header.SearchHeaderLayout
import com.leisurely.people.enjoyd.ui.search.recent.SearchRecentLayout
import com.leisurely.people.enjoyd.ui.search.searchResult.SearchResultLayout
import org.koin.dsl.module

/**
 * Search 패키지 내 DI 설정을 위한 파일
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.20
 */

/** 검색 뷰 모듈(DI) 설정 */
val searchViewModule = module {
    factory { (activity: SearchActivity, layout: View, vm: SearchViewModel) ->
        SearchHeaderLayout(activity, layout, vm)
    }
    factory { (activity: SearchActivity, layout: View, vm: SearchViewModel) ->
        SearchBasicLayout(activity, layout, vm)
    }
    factory { (activity: SearchActivity, layout: View, vm: SearchViewModel) ->
        SearchRecentLayout(activity, layout, vm)
    }
    factory { (activity: SearchActivity, layout: View, vm: SearchViewModel) ->
        SearchResultLayout(activity, layout, vm)
    }
}
