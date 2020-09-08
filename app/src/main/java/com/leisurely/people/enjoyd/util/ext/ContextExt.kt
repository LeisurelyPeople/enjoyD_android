package com.leisurely.people.enjoyd.util.ext

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.edit
import com.leisurely.people.enjoyd.util.Constant

/**
 * Context Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */


/** 네트워크 연결 상태 확인 메소드 */
fun Context.isNetworkConnected(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}

/** SharedPreference 저장된 값을 가져오는 메소드 */
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

/** SharedPreference 값 저장을 위한 메소드 */
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