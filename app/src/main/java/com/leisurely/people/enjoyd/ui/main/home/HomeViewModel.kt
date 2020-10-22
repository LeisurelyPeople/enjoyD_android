package com.leisurely.people.enjoyd.ui.main.home

import android.util.Log
import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasWatchingResponse
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.data.repository.DramasBannerRepository
import com.leisurely.people.enjoyd.data.repository.DramasTagsRepository
import com.leisurely.people.enjoyd.data.repository.DramasWatchingRepository
import com.leisurely.people.enjoyd.model.DramasTagsModel
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.coroutine.onError
import com.leisurely.people.enjoyd.util.coroutine.onSuccess
import com.leisurely.people.enjoyd.util.coroutine.zip
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * 홈탭 관련 ViewModel class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeViewModel(
    private val dramasBannerRepository: DramasBannerRepository,
    private val dramasWatchingRepository: DramasWatchingRepository,
    private val dramasTagsRepository: DramasTagsRepository,
    private val dramasRepository: DramaRepository
) : BaseViewModel() {

    /** 드라마 배너 정보를 가지고 있는 LiveData */
    private val _dramasBannerData: MutableLiveData<DramasBannerResponse> = MutableLiveData()
    val dramasBannerData: LiveData<List<DramasBannerResponse>> = _dramasBannerData.map {
        listOf(it)
    }

    /** 시청중인 드라마 정보를 가지고 있는 LiveData */
    private val _dramasWatchingData: MutableLiveData<List<DramasWatchingResponse>> =
        MutableLiveData(listOf())
    val dramasWatchingData: LiveData<List<DramasWatchingResponse>> = _dramasWatchingData

    /** 드라마 태그 정보를 가지고 있는 LiveData */
    private val _dramasTagsData: MutableLiveData<List<DramasTagsModel>> = MutableLiveData(listOf())
    val dramasTagsInfo: LiveData<List<DramasTagsModel>> = _dramasTagsData

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

    /** 배너, 시청중인 드라마, 드라마 태그 정보 가져오는 메소드 */
    fun getHomeData() {
        viewModelScope.launch {
            zip(
                dramasBannerRepository.getDramasBannerUsingFlow(),
                dramasWatchingRepository.getDramasWatchingUsingFlow(1, 10),
                dramasTagsRepository.getDramasTagsUsingFlow()
            ) { bannerResponse, watchingDramasResponse, tagsModel ->
                _dramasBannerData.value = bannerResponse
                _dramasWatchingData.value = watchingDramasResponse.results
                delay(500) // 텍스트로만 이루어져 있는 태그 정보들이 스크린에 먼저 그려져서 어색하게 보여 delay 값 추가
                _dramasTagsData.value = tagsModel

                // 태그 첫번째 값으로 드라마 리스트 조회
                _dramasTagsData.value?.firstOrNull()?.let {
                    getDramaItemsUsingTags(1, it.name)
                }
            }
                .onStart { showLoading() }
                .onCompletion { hideLoading() }
                .catch { handleException(throwable = it) }
                .collect()
        }
    }

    /** 태그값을 통해 드라마 정보를 가져오는 메소드 */
    fun getDramaItemsUsingTags(page: Int, tag: String) {
        _tag.value = tag
        _page.value = page
        viewModelScope.launch {
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
        }
    }
}