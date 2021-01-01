package com.leisurely.people.enjoyd.ui.main.mypage

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.leisurely.people.enjoyd.R
import kotlinx.android.synthetic.main.view_my_page_tab.view.*

/**
 * 마이 페이지 화면에 있는 상단 커스텀 탭 뷰
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class MyPageTabView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    ConstraintLayout(context, attrs) {

    private val selectedTextColor = resources.getColor(R.color.color_grey_0, context.theme)
    private val unSelectedTextColor = resources.getColor(R.color.color_grey_200, context.theme)

    private val selectedBackgroundColor = resources.getColor(R.color.color_grey_400, context.theme)
    private val unSelectedBackgroundColor =
        resources.getColor(R.color.color_grey_500, context.theme)

    private var currentPosition: Int = -1

    var tabSelectedListener: OnTabSelectedListener? = null
    var selectedPosition: Int = currentPosition
        set(value) {
            field = value
            setSelectedStateForView(value)
        }

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_my_page_tab, this, false)
        addView(view)

        ll_first_tab_layout.setOnClickListener {
            tabSelectedListener?.onTabSelected(0)
            setSelectedStateForView(0)
        }
        ll_second_tab_layout.setOnClickListener {
            tabSelectedListener?.onTabSelected(1)
            setSelectedStateForView(1)
        }
    }

    private fun setSelectedStateForView(selectedPosition: Int) {
        if (selectedPosition == currentPosition) return

        currentPosition = selectedPosition

        ll_first_tab_layout.setBackgroundColor(
            if (selectedPosition == 0) selectedBackgroundColor
            else unSelectedBackgroundColor
        )
        ll_second_tab_layout.setBackgroundColor(
            if (selectedPosition == 1) selectedBackgroundColor
            else unSelectedBackgroundColor
        )
        tv_first_tab_title.setTextColor(
            if (selectedPosition == 0) selectedTextColor
            else unSelectedTextColor
        )
        tv_second_tab_title.setTextColor(
            if (selectedPosition == 1) selectedTextColor
            else unSelectedTextColor
        )
    }

    interface OnTabSelectedListener {
        fun onTabSelected(position: Int)
    }
}

@BindingAdapter("firstTabTitle")
fun MyPageTabView.bindFirstTabTitle(title: String?) {
    tv_first_tab_title.text = title
}

@BindingAdapter("secondTabTitle")
fun MyPageTabView.bindSecondTabTitle(title: String?) {
    tv_second_tab_title.text = title
}