package com.leisurely.people.enjoyd.ui.search.header

import android.util.Log
import android.view.View
import android.widget.Toast
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel
import com.leisurely.people.enjoyd.util.ext.hideKeyboard
import kotlinx.android.synthetic.main.layout_search_header.view.*

/**
 * 검색화면 헤더 레이아웃 (검색 바)
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchHeaderLayout(activity: SearchActivity, layout: View, vm: SearchViewModel) {
    val tag = this.javaClass.canonicalName

    private val searchHeaderEditText: SearchHeaderEditText by lazy {
        SearchHeaderEditText(activity).apply { init(activity, vm) }
    }

    /**
     * 검색 EditText 에 한 번이라도 포커스가 들어올 시 실행되는 리스너
     * 추천 레이아웃을 가린 후, 최근 검색어 레이아웃을 보여준다.
     */
    private val queryOneFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
        Log.i(tag, "$hasFocus")
        if (hasFocus) {
            // 빈 화면일 때, 한번이라도 클릭했을 때 & 안 클릭했을 때 보여주는 화면이 다르므로 vm 의 값 변경
            vm.initClick.set(true)
            vm.isTyping.set(true)

            // 만약 한번이라도 실행되면 바로 없애준다.
            layout.et_query.onFocusChangeListener = null
        }
    }

    init {
        layout.apply {
            et_query.onFocusChangeListener = queryOneFocusChangeListener

            btn_back.setOnClickListener {
                layout.hideKeyboard()
            }

            btn_search.setOnClickListener {
                // 만약 한 글자도 입력하지 않았을 경우 겁색하지 않는다.
                if (vm.query.get().isNullOrEmpty()) {
                    Toast.makeText(this.context, "검색하기 위해 한 글자라도 입력해야 합니다.", Toast.LENGTH_SHORT)
                        .show()
                }
                // 어떤 글자라도 입력했다면 검색을 시작한다.
                else {
                    layout.hideKeyboard()
                }
            }
        }
    }

}
