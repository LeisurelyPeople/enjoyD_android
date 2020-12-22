package com.leisurely.people.enjoyd.data.repository.evaluation

import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import com.leisurely.people.enjoyd.data.remote.source.evaluation.DramaEvaluationDataSource
import io.reactivex.Completable
import io.reactivex.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * 드라마 평가 관련 Repository 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.11
 */
class DramaEvaluationRepository(private val dramaEvaluationDataSource: DramaEvaluationDataSource) {

    fun getDramaEvaluationData(
        page: Int,
        pageSize: Int
    ): Single<PagingResponse<DramaEvaluationResponse>> {
        return dramaEvaluationDataSource.getDramasRatings(page, pageSize)
    }

    fun getDramaEvaluationDataUsingCoroutine(page: Int, pageSize: Int) = flow {
        emit(dramaEvaluationDataSource.getDramasRatingsUsingCoroutine(page, pageSize))
    }.flowOn(Dispatchers.IO)

    fun postDramaEvaluationData(data: List<DramaEvaluationRequest>): Completable {
        return dramaEvaluationDataSource.postDramasRatings(data)
    }

    fun postDramaEvaluationDataUsingCoroutine(data: List<DramaEvaluationRequest>) = flow {
        emit(dramaEvaluationDataSource.postDramasRatingsUsingCoroutine(data))
    }.flowOn(Dispatchers.IO)
}