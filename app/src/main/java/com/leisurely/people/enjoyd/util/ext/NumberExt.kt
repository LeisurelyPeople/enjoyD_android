package com.leisurely.people.enjoyd.util.ext

/**
 * Number 를 상속받는 Type 들의 Extension 관리 파일
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.27
 */

/** 한 자리 수인 경우 앞에 0을 붙여주는 텍스트를 반환한다. */
fun Int.styledNumber(): String = if (this < 10 && this > -10) {
    "0$this"
} else
    "$this"
