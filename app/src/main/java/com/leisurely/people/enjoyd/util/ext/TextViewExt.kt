package com.leisurely.people.enjoyd.util.ext

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.PrecomputedTextCompat
import androidx.databinding.BindingAdapter

@BindingAdapter("htmlText")
fun TextView.setHtmlText(htmlText: String) {
    text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(htmlText, Html.FROM_HTML_MODE_LEGACY)
    } else {
        Html.fromHtml(htmlText)
    }
}

// 장문의 텍스트인 경우 사용 (메모리 효율적으로 사용)
@BindingAdapter("asyncText")
fun AppCompatTextView.setAsyncText(text: String?) {
    text?.let {
        setTextFuture(PrecomputedTextCompat.getTextFuture(it, textMetricsParamsCompat, null))
    }
}

@BindingAdapter("customFont")
fun TextView.setCustomFont(fontName: String) {
    typeface = Typeface.createFromAsset(context.assets, "font/$fontName")
}