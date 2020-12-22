package com.leisurely.people.enjoyd.ui.main.evaluation

import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.request.evaluation.DramaEvaluationRequest
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import com.leisurely.people.enjoyd.data.repository.evaluation.DramaEvaluationRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 평가하기 탭 관련 ViewModel 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class EvaluationViewModel(private val dramaEvaluationRepository: DramaEvaluationRepository) :
    BaseViewModel() {

    /** 로딩 관련 LiveData */
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /** 페이징 처리 부분에서 다음 항목의 데이터를 가지고 있는지 판단하는 boolean 값을 담고 있는 LiveData */
    private val _hasNextData: MutableLiveData<Boolean> = MutableLiveData(true)
    val hasNextData: LiveData<Boolean> = _hasNextData

    /** 드라마 평가하기 정보를 담고 있는 LiveData */
    private val _evaluationDramasLiveData: MutableLiveData<List<DramaEvaluationResponse>> =
        MutableLiveData(listOf())
    val evaluationDramasLiveData: LiveData<List<DramaEvaluationResponse>> =
        _evaluationDramasLiveData

    /** 드라마 정보(평가하기) 가져오기 */
    fun getDramasList(page: Int) {
        viewModelScope.launch {
            dramaEvaluationRepository.getDramaEvaluationDataUsingCoroutine(page, 10)
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { handleException(throwable = it) }
                .collect {
                    if (page == 1) { // 첫 데이터 조회 or 새로운 태그에 대한 드라마 조회일 경우
                        _evaluationDramasLiveData.value = it.results
                    } else { // 페이징 처리 일 경우
                        _evaluationDramasLiveData.value =
                            _evaluationDramasLiveData.value?.plus(it.results)
                    }
                }
        }
    }

    /** 사용자가 입력한 드라마 평점 적용하기 */
    fun onDramaRatingChanged(rating: Float, pk: Int) {
        viewModelScope.launch {
            dramaEvaluationRepository.postDramaEvaluationDataUsingCoroutine(
                listOf(DramaEvaluationRequest(rating, pk))
            )
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { handleException(throwable = it) }
                .collect {
                    _evaluationDramasLiveData.value?.find {
                        it.pk == pk
                    }?.let {
                        _evaluationDramasLiveData.value = evaluationDramasLiveData.value?.minus(it)
                    }
                }
        }

    }
}