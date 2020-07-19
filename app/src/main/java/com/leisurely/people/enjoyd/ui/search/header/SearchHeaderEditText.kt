package com.leisurely.people.enjoyd.ui.search.header

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.leisurely.people.enjoyd.ui.search.SearchActivity
import com.leisurely.people.enjoyd.ui.search.SearchViewModel

/**
 * 검색화면 헤더 레이아웃에서 사용하는 EditText
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.07
 */
class SearchHeaderEditText(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {
    private val tag = javaClass.canonicalName

    lateinit var activity: SearchActivity
    lateinit var vm: SearchViewModel

    /** 초기 설정을 진행한다. */
    fun init(activity: SearchActivity, vm: SearchViewModel) {
        this.activity = activity
        this.vm = vm
    }
}
