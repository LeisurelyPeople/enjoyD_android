package com.leisurely.people.enjoyd.data.remote.source

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService

/**
 * 문의/질문 등을 보내는 API 와 관련된 DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.23
 */
class QuestionRemoteDataSource(private val enjoyDService: EnjoyDService) {

    suspend fun postSupportQuestionCreate(content: String) {
        enjoyDService.postSupportQuestionCreate(hashMapOf("content" to content))
    }
}