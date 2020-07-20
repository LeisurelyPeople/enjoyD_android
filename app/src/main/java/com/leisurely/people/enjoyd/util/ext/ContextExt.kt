package com.leisurely.people.enjoyd.util.ext

import android.app.Activity
import android.content.Context
import androidx.core.content.edit
import com.leisurely.people.enjoyd.util.Constant

/**
 * Context Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

fun Context.putSharedPreference(key: String, data: Any) {
    getSharedPreferences(Constant.PREF_NAME, Activity.MODE_PRIVATE).run {
        edit(true) {
            when (data) {
                is String -> putString(key, data)
                is Boolean -> putBoolean(key, data)
                is Int -> putInt(key, data)
                is Float -> putFloat(key, data)
                is Long -> putLong(key, data)
            }
        }
    }
}

@Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")
fun <T> Context.getSharedPreference(key: String, defaultData: T): T {
    val sharedPreferences = getSharedPreferences(Constant.PREF_NAME, Activity.MODE_PRIVATE)
    return with(sharedPreferences) {
        when (defaultData) {
            is String -> getString(key, defaultData)
            is Boolean -> getBoolean(key, defaultData)
            is Int -> getInt(key, defaultData)
            is Float -> getFloat(key, defaultData)
            is Long -> getLong(key, defaultData)
            else -> null
        } as T
    }
}