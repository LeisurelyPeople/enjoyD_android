package com.leisurely.people.enjoyd.data.remote.source.evaluation

import com.leisurely.people.enjoyd.data.remote.api.EnjoyDService
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import io.reactivex.Completable
import io.reactivex.Single

/**
 * 드라마 평가 관련 DataSource 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.11
 */
class DramaEvaluationDataSource(private val enjoyDService: EnjoyDService) {

    fun getDramasInfoRating(
        page: Int,
        pageSize: Int
    ): Single<PagingResponse<DramaEvaluationResponse>> {
        return enjoyDService.getDramasInfoRating(page, pageSize)
    }

    fun postDramasInfoRating(data: List<DramaEvaluationRequest>): Completable {
        return enjoyDService.postDramasInfoRating(hashMapOf("drama_rating_infos" to data))
    }
}