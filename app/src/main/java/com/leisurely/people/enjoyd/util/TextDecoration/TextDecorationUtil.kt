package com.leisurely.people.enjoyd.util.TextDecoration

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan

/**
 * 텍스트 데코레이션을 위한 유틸 클래스
 *
 * @author ricky
 * @since v11.4.0 / 2020.07.01
 */
object TextDecorationUtil {
    fun generateStyledText(styledTexts: List<StyledText>): SpannableStringBuilder {
        val ssb = SpannableStringBuilder()

        styledTexts.forEach { styledText ->
            val prevIndex = ssb.toString().length
            ssb.append(styledText.text)
            val nextIndex = ssb.toString().length

            // 색상 설정
            styledText.color?.let {
                ssb.setSpan(
                    ForegroundColorSpan(it),
                    prevIndex,
                    nextIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

            // 굵기 설정
            styledText.isBold?.let {
                ssb.setSpan(
                    StyleSpan(Typeface.BOLD),
                    prevIndex,
                    nextIndex,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

//            // 기타 설정
//            styledText.otherSpan?.forEach { span ->
//                when (span) {
//
//                }
//            }
        }

        return ssb
    }
}
