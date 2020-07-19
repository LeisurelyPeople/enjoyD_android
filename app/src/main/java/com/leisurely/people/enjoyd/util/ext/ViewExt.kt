package com.leisurely.people.enjoyd.util.ext

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.BindingAdapter
import io.reactivex.subjects.Subject

/**
 * View Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** 키보드 숨기기 */
fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    imm?.hideSoftInputFromWindow(windowToken, 0)
}

@BindingAdapter("onClick")
fun View.bindClick(onClick: Subject<Unit>) {
    this.setOnClickListener {
        onClick.onNext(Unit)
    }
}