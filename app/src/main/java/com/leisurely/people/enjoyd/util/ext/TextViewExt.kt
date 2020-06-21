package com.leisurely.people.enjoyd.util.ext

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.BindingAdapter

/**
 * TextView Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** Html TextView */
@BindingAdapter("htmlText")
fun TextView.setHtmlText(htmlText: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}

/** Prefetch TextView (장문의 텍스트인 경우 사용하며 메모리를 효율적으로 관리함) */
@BindingAdapter("asyncText")
fun AppCompatTextView.setAsyncText(text: String?) {
    text?.let {
        setTextFuture(PrecomputedTextCompat.getTextFuture(it, textMetricsParamsCompat, null))
    }
}

/** Font 설정*/
@BindingAdapter("customFont")
fun TextView.setCustomFont(fontName: String) {
    typeface = Typeface.createFromAsset(context.assets, "font/$fontName")
}