package com.leisurely.people.enjoyd.ui.main.home

import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.data.repository.DramasBannerRepository
import com.leisurely.people.enjoyd.data.repository.DramasTagsRepository
import com.leisurely.people.enjoyd.model.DramasTagsModel
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
    private val _dramasBannerData: LiveData<DramasBannerResponse> = liveData {
        dramasBannerRepository.getDramasBanner()
            .onSuccess { emit(it) }
            .onError(::handleException)
    }
    val dramasBannerData: LiveData<List<DramasBannerResponse>>
        get() = _dramasBannerData.map {
            listOf(it)
        }

    /** 드라마 태그 정보를 가지고 있는 LiveData */
    val dramasTagsInfo: LiveData<List<DramasTagsModel>> = liveData {
        showLoading()
        dramasTagsRepository.getDramasTags()
            .onSuccess {
                val items = it.results.map(DramasTagsResponse::toDramaTagsModel)
                if (items.isNotEmpty()) {
                    items[0].isSelected = true // 첫번째 아이템 클릭 상태로 만들기
                    emit(items)
                    getDramaItemsUsingTags(1, items[0].name)  // 첫번째 값 태그에 해당되는 드라마 정보 조회
                }
            }
            .onError(::handleException)
        hideLoading()
    }

    /** 현재 데이터 조회 페이지 값에 해당 되는 LiveData (default : 1) */
    private val _page: MutableLiveData<Int> = MutableLiveData(1)
    val page: LiveData<Int> = _page

    /** 현재 활성화된 태그 정보를 가지고 있는 LiveData */
    private val _tag: MutableLiveData<String> = MutableLiveData("")
    val tag: LiveData<String> = _tag

    /** 드라마 아이템 정보들을 가지고 있는 LiveData */
    private val _dramaItems: MutableLiveData<List<DramasItemResponse>> =
        MutableLiveData(mutableListOf())
    val dramaItems: LiveData<List<DramasItemResponse>> = _dramaItems

    /** 현재 조회한 데이터 이후로 추가 데이터가 남아있는지 판별하기 위한 LiveData */
    private val _existsMoreDramaItems: MutableLiveData<Boolean> = MutableLiveData(false)
    val existsMoreDramaItems: LiveData<List<Unit>>
        get() = _existsMoreDramaItems.map {
            if (it) {
                listOf(Unit)
            } else {
                emptyList()
            }
        }

    /** 태그값을 통해 드라마 정보를 가져오는 메소드 */
    fun getDramaItemsUsingTags(page: Int, tag: String) {
        _tag.value = tag
        _page.value = page
        viewModelScope.launch {
            showLoading()
            dramasRepository.getDramasUsingTags(tag, page, 10)
                /** 응답값이 성공으로 떨어질 경우 */
                .onSuccess {
                    _existsMoreDramaItems.value = it.next
                    if (page == 1) { // 첫 데이터 조회 or 새로운 태그에 대한 드라마 조회일 경우
                        _dramaItems.value = it.results
                    } else { // 페이징 처리 일 경우
                        _dramaItems.value = _dramaItems.value?.plus(it.results)
                    }
                }
                /** 응답값이 실패로로 떨어질 경우 */
                .onError(::handleException)
            hideLoading()
        }
    }
}