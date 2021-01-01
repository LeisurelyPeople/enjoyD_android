package com.leisurely.people.enjoyd.data.repository

import com.leisurely.people.enjoyd.data.remote.source.QuestionRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 문의/질문 등을 보내기 위한 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.23
 */
class QuestionRepository(private val questionRemoteDataSource: QuestionRemoteDataSource) {

    suspend fun postSupportQuestionCreate(content: String) = flow {
        emit(questionRemoteDataSource.postSupportQuestionCreate(content))
    }.flowOn(Dispatchers.IO)
}