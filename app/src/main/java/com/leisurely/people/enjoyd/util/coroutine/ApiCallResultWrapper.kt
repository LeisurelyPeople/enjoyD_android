package com.leisurely.people.enjoyd.util.coroutine

/**
 * safeApiCall 메소드 사용 시 결과를 wrapping 하는 역할의 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.30
 */
sealed class ApiCallResultWrapper<out T> {
    data class Success<out T>(val value: T) : ApiCallResultWrapper<T>()
    data class Error(val throwable: Throwable) : ApiCallResultWrapper<Nothing>()
}