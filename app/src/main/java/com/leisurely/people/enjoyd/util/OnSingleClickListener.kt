package com.leisurely.people.enjoyd.util

import android.view.View
import java.util.concurrent.atomic.AtomicBoolean

/**
 * RxJava 를 사용하지 않는 클릭 리스너
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.24
 */
class OnSingleClickListener(
    private val clickListener: () -> Unit,
    private val intervalMs: Long = 1000
) : View.OnClickListener {
    private var canClick = AtomicBoolean(true)

    override fun onClick(v: View?) {
        if (canClick.getAndSet(false)) {
            v?.run {
                postDelayed({ canClick.set(true) }, intervalMs)
                clickListener()
            }
        }
    }
}
