package com.leisurely.people.enjoyd.model

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse

/**
 * 기존 모델 클래스를 래핑하는 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.06
 */
data class ResultWrapperModel<T>(val data: T)

fun <T : Any> PagingResponse<T>.toResultWrapper(): ResultWrapperModel<List<T>> {
    return ResultWrapperModel(results)
}

fun <T: Any> Iterable<T>.toResultWrapper(): ResultWrapperModel<List<T>> {
    return ResultWrapperModel(this.toMutableList())
}