package com.leisurely.people.enjoyd.util.TextDecoration

import android.text.style.CharacterStyle

/**
 * 스타일링할 데이터 클래스
 *
 * @author ricky
 * @since v11.4.0 / 2020.07.01
 */
data class StyledText(
    var text: String,
    var color: Int? = null,
    var isBold: Boolean? = null,
    var otherSpan: List<String>? = null
)
