package com.leisurely.people.enjoyd.ui.main.home

import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.data.repository.DramasBannerRepository
import com.leisurely.people.enjoyd.data.repository.DramasTagsRepository
import com.leisurely.people.enjoyd.model.ResultWrapperModel
import com.leisurely.people.enjoyd.model.toResultWrapper
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.coroutine.onError
import com.leisurely.people.enjoyd.util.coroutine.onSuccess
import kotlinx.coroutines.*

/**
 * 홈탭 관련 ViewModel class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeViewModel(
    private val dramasBannerRepository: DramasBannerRepository,
    private val dramasTagsRepository: DramasTagsRepository,
    private val dramasRepository: DramaRepository
) : BaseViewModel() {

    /** 드라마 배너 정보를 가지고 있는 LiveData */
    val dramasBannerData: LiveData<DramasBannerResponse> = liveData {
        dramasBannerRepository.getDramasBanner()
            .onSuccess { emit(it) }
            .onError(::handleException)
    }

    /** 드라마 태그 정보를 가지고 있는 LiveData */
    val dramasTagsInfo: LiveData<ResultWrapperModel<List<DramasTagsResponse>>> = liveData {
        showLoading()
        dramasTagsRepository.getDramasTags()
            .onSuccess { emit(it.toResultWrapper()) }
            .onError(::handleException)
        hideLoading()
    }

    /** 현재 활성화된 태그 정보를 가지고 있는 LiveData */
    private val _tag: MutableLiveData<String> = MutableLiveData("")
    val tag: LiveData<String> = _tag

    /** 드라마 아이템 정보들을 가지고 있는 LiveData */
    private val _dramaItems: MutableLiveData<ResultWrapperModel<List<DramasItemResponse>>> =
        MutableLiveData()
    val dramaItems: LiveData<ResultWrapperModel<List<DramasItemResponse>>> = _dramaItems

    /** 현재 조회한 데이터 이후로 추가 데이터가 남아있는지 판별하기 위한 LiveData */
    private val _existsDramaItems: MutableLiveData<Boolean> = MutableLiveData(false)

    /** 드라마 전체보기 버튼을 보여 줄지 말지에 대한 값을 가지고 있는 LiveData */
    val dramaViewAllItem: LiveData<List<ResultWrapperModel<Unit>>>
        get() = _existsDramaItems.map {
            if (it) {
                listOf(ResultWrapperModel(Unit))
            } else {
                emptyList()
            }
        }

    /** 태그값을 통해 드라마 정보를 가져오는 메소드 */
    fun getDramaItemsUsingTags(tag: String) {
        _tag.value = tag
        viewModelScope.launch {
            showLoading()
            dramasRepository.getDramasUsingTags(tag, 1, 10)
                /** 응답값이 성공으로 떨어질 경우 */
                .onSuccess {
                    _existsDramaItems.value = it.next
                    _dramaItems.value = it.toResultWrapper()
                }
                /** 응답값이 실패로로 떨어질 경우 */
                .onError(::handleException)
            hideLoading()
        }
    }
}