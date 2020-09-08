package com.leisurely.people.enjoyd.util

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.MetricAffectingSpan

/**
 * 커스텀 TypeFace를 적용하기 위한 유틸 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.08
 */
class CustomTypefaceSpan(private val typeface: Typeface?) : MetricAffectingSpan() {
    override fun updateMeasureState(textPaint: TextPaint) {
        applyCustomTypeFace(textPaint, typeface)
    }

    override fun updateDrawState(textPaint: TextPaint) {
        applyCustomTypeFace(textPaint, typeface)
    }

    private fun applyCustomTypeFace(paint: Paint, tf: Typeface?) {
        paint.typeface = tf
    }
}