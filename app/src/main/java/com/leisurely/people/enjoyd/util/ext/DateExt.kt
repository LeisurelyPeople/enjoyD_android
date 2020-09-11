package com.leisurely.people.enjoyd.util.ext

import com.leisurely.people.enjoyd.util.time.TimePoint
import java.text.SimpleDateFormat
import java.util.*

/**
 * 날짜(포맷) 관련 Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.23
 */

fun TimePoint.formatToViewDate(): String {
    return this.format("yyyy.MM.dd")
}

fun TimePoint.formatToServerDate(): String {
    return this.format("yyyy-MM-dd")
}

fun String.convertDateFormatToTimeStamp(): Long {
    val date = getAccessTokenDateFormat().parse(this)
    return date?.let {
        Calendar.getInstance().apply { time = date }.timeInMillis
    } ?: -1L
}

fun getViewDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

fun getServerDateFormat(): SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

fun getAccessTokenDateFormat(): SimpleDateFormat =
    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())