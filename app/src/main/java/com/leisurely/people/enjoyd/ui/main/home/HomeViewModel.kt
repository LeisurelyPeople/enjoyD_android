package com.leisurely.people.enjoyd.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.data.repository.DramasBannerRepository
import com.leisurely.people.enjoyd.data.repository.DramasTagsRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.coroutine.ResultWrapper
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
    val dramasTagsInfo: LiveData<PagingResponse<DramasTagsResponse>> = liveData {
        dramasTagsRepository.getDramasTags()
            .onSuccess { emit(it) }
            .onError(::handleException)
    }

    /** 현재 활성화된 태그 정보를 가지고 있는 LiveData */
    private val _tag: MutableLiveData<String> = MutableLiveData("")
    val tag: LiveData<String> = _tag

    /** 드라마 정보를 가져오기 위한 page 값 */
    private val _page: MutableLiveData<Int> = MutableLiveData(1)
    val page: LiveData<Int> = _page

    /** 드라마 아이템 정보들을 가지고 있는 LiveData */
    private val _dramaItems: MutableLiveData<PagingResponse<DramasItemResponse>> = MutableLiveData()
    val dramaItems: LiveData<PagingResponse<DramasItemResponse>> = _dramaItems

    /** 태그값을 통해 드라마 정보를 가져오는 메소드 */
    fun getDramaItemsUsingTags(tag: String, page: Int) {
        _page.value = page
        viewModelScope.launch {
            showLoading()
            dramasRepository.getDramasUsingTags(tag, page, 10)
                /** 응답값이 성공으로 떨어질 경우 */
                .onSuccess {
                    /** 기존에 활성화된 태그와 다른 태그를 사용자가 클릭했을 경우 */
                    val items = if (_tag.value != tag) {
                        it.results
                    } else {
                        it.results.plus(_dramaItems.value?.results ?: mutableListOf())
                    }
                    _tag.value = tag
                    _dramaItems.value = PagingResponse(it.totalCount, it.next, items)
                }
                /** 응답값이 실패로로 떨어질 경우 */
                .onError(::handleException)
            hideLoading()
        }
    }
}