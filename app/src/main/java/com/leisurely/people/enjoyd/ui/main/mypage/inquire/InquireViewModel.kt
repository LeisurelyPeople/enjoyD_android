package com.leisurely.people.enjoyd.ui.main.mypage.inquire

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leisurely.people.enjoyd.data.repository.QuestionRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 문의/제안하기 탭 관련 ViewModel 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class InquireViewModel(private val questionRepository: QuestionRepository) : BaseViewModel() {

    /** 피드백 정보 LiveData */
    private val _feedbackLiveData: MutableLiveData<String> = MutableLiveData("")
    val feedbackLiveData: MutableLiveData<String> = _feedbackLiveData

    fun sendFeedback() {
        val content = _feedbackLiveData.value ?: kotlin.run {
            showToast("문의 또는 제안하실 드라마 정보를 입력해주세요.")
            return
        }
        viewModelScope.launch {
            questionRepository.postSupportQuestionCreate(content)
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { handleException(throwable = it) }
                .collect {
                    showToast("보내기 성공!")
                    _feedbackLiveData.value = ""
                }
        }
    }
}