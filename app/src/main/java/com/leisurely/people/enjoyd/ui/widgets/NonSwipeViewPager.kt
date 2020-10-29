package com.leisurely.people.enjoyd.ui.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.animation.DecelerateInterpolator
import android.widget.Scroller
import androidx.viewpager.widget.ViewPager

/**
 * Swipe 기능을 막을 수 있는 ViewPager 커스텀 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class NonSwipeViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    init {
        setMyScroller()
    }

    var swipeEnabled = false

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return when (swipeEnabled) {
            true -> super.onInterceptTouchEvent(ev)
            false -> false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return when (swipeEnabled) {
            true -> super.onTouchEvent(ev)
            false -> false
        }
    }

    private fun setMyScroller() {
        try {
            val viewpager = ViewPager::class.java
            viewpager.getDeclaredField("mScroller").run {
                isAccessible = true
                set(this@NonSwipeViewPager, MyScroller(context))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class MyScroller internal constructor(context: Context) :
        Scroller(context, DecelerateInterpolator()) {

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
            super.startScroll(startX, startY, dx, dy, 500)
        }
    }

}