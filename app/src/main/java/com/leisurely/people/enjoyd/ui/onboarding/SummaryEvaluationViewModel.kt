package com.leisurely.people.enjoyd.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import com.leisurely.people.enjoyd.data.repository.evaluation.DramaEvaluationRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.ext.applySingleSchedulers
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import com.leisurely.people.enjoyd.util.observer.DisposableSingleObserver

/**
 * 온보딩 과정중의 평가하기 뷰모델
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.09
 */
class SummaryEvaluationViewModel(private val dramaEvaluationRepository: DramaEvaluationRepository) :
    BaseViewModel() {

    /** 드라마 평가 정보들을 담고 있는 LiveData */
    private val _dramaEvaluationItems: MutableLiveData<List<DramaEvaluationResponse>> =
        MutableLiveData()
    val dramaEvaluationItems: LiveData<List<DramaEvaluationResponse>> = _dramaEvaluationItems

    /** 사용자가 평가한 드라마 정보를 담고 있는 LiveData */
    private val _dramaEvaluationRequest: MutableLiveData<List<DramaEvaluationRequest>> =
        MutableLiveData(mutableListOf())
    val dramaEvaluationRequest: LiveData<List<DramaEvaluationRequest>> =
        _dramaEvaluationRequest

    /** 메인화면으로 이동하기 위한 LiveData */
    private val _startMain: LiveEvent<Unit> = LiveEvent()
    val startMain: LiveEvent<Unit> = _startMain

    /** 드라마 정보 가져오는 메소드 */
    fun getDramaEvaluationItems() {
        dramaEvaluationRepository.getDramaEvaluationData(1, 10)
            .applySingleSchedulers()
            .doOnSuccess {
                if (it.results.isEmpty()) _startMain.value = null
            }
            .subscribeWith(object :
                DisposableSingleObserver<PagingResponse<DramaEvaluationResponse>>() {
                override fun onSuccess(result: PagingResponse<DramaEvaluationResponse>) {
                    _dramaEvaluationItems.value = result.results
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    _startMain.value = null
                }
            }).addDisposable()
    }

    fun onSkipScreen() {
        _startMain.value = null
    }

    /**
     * rating 0 일 경우
     *  - 기존에 저장되어 있던 객체가 있는 경우에만 삭제
     *
     * rating 0 이상일 경우
     *  - 기존에 저장되어 있던 객체가 있는 경우 rating 값 수정, 아닌 경우 객체 추가
     *
     */
    fun onDramaRatingChanged(rating: Float, idx: Int) {
        val item = _dramaEvaluationRequest.value?.find {
            it.dramaInfoPk == idx
        }
        if (rating == 0F) {
            item?.let {
                _dramaEvaluationRequest.value = _dramaEvaluationRequest.value?.minus(item)
            }
        } else {
            item?.let {
                it.rating = rating
            } ?: kotlin.run {
                _dramaEvaluationRequest.value =
                    _dramaEvaluationRequest.value?.plus(DramaEvaluationRequest(rating, idx))
            }
        }
    }

    fun onEvaluationComplete() {
        // TODO 평가한 드라마정보 서버로 전송하는 작업
    }
}