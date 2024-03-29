package com.leisurely.people.enjoyd.util.TextDecoration

/**
 * 스타일링할 데이터 클래스
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
data class StyledText(
    var text: String,
    var color: Int? = null,
    var isBold: Boolean? = null,
    var otherSpan: List<String>? = null
)
