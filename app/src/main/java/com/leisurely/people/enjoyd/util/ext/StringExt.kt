package com.leisurely.people.enjoyd.util.ext

/**
 * String Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.25
 */
fun String.convertToServerDate(): String {
    val formatIn = getViewDateFormat()
    val formatOut = getServerDateFormat()
    val date = formatIn.parse(this)
    return date?.let {
        formatOut.format(it)
    } ?: ""
}

fun String.convertToViewDate(): String {
    val formatIn = getServerDateFormat()
    val formatOut = getViewDateFormat()
    val date = formatIn.parse(this)
    return date?.let {
        formatOut.format(it)
    } ?: ""
}