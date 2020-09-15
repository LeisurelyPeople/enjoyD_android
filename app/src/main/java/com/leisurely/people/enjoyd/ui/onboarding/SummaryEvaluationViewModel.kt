package com.leisurely.people.enjoyd.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
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

    private val _dramaEvaluationItems: MutableLiveData<List<DramaEvaluationResponse>> =
        MutableLiveData()
    val dramaEvaluationItems: LiveData<List<DramaEvaluationResponse>> = _dramaEvaluationItems

    private val _startMain: LiveEvent<Unit> = LiveEvent()
    val startMain: LiveEvent<Unit> = _startMain

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
}