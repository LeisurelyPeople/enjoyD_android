package com.leisurely.people.enjoyd.util.ext

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.leisurely.people.enjoyd.util.OnSingleClickListener
import io.reactivex.subjects.Subject

/**
 * View Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(value: Boolean) {
    isVisible = value
}

@BindingAdapter("android:invisibleIf")
fun View.setInvisibleIf(value: Boolean) {
    isInvisible = value
}

@BindingAdapter("android:goneIf")
fun View.setGoneIf(value: Boolean) {
    isGone = value
}

/** 일반 뷰에서 키보드 숨기기 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

/** 액티비티에서 키보드 숨기기 */
fun AppCompatActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

/** Context 에서 키보드 숨기기 */
fun Context.hideKeyboard(view: View) {
    view.hideKeyboard()
}

@BindingAdapter("onClick")
fun View.bindClick(onClick: Subject<Unit>) {
    this.setOnClickListener {
        onClick.onNext(Unit)
    }
}

/** RxJava 를 사용하지 않는 클릭리스너를 등록한다. */
@BindingAdapter("onClick")
fun View.setOnSingleClickListener(onClickAction: (() -> Unit)?) {
    onClickAction?.also {
        setOnClickListener(
            OnSingleClickListener(
                it
            )
        )
    } ?: setOnClickListener(null)
}
