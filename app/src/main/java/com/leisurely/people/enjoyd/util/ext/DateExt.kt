package com.leisurely.people.enjoyd.util.ext

import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜(포맷) 관련 Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.23
 */

fun getViewDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
fun getServerDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun Date.formatToServerDate(): String {
    return getServerDateFormat().format(this)
}

fun Date.formatToViewDate(): String {
    return getViewDateFormat().format(this)
}

fun Date.add(field: Int, amount: Int): Date {
    Calendar.getInstance().apply {
        time = this@add
        add(field, amount)
        return time
    }
}