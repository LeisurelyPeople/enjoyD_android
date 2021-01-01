package com.leisurely.people.enjoyd.ui.main.mypage.dibs

import androidx.lifecycle.*
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.mypage.DramasBookmarkResponse
import com.leisurely.people.enjoyd.data.repository.DramasBookmarkRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * 찜한 드라마 탭 관련 ViewModel 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class SavedDramasViewModel(private val dramasBookmarkRepository: DramasBookmarkRepository) :
    BaseViewModel() {

    /** 로딩 관련 LiveData */
    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    /** 페이징 처리 부분에서 다음 항목의 데이터를 가지고 있는지 판단하는 boolean 값을 담고 있는 LiveData */
    private val _hasNextData: MutableLiveData<Boolean> = MutableLiveData(true)
    val hasNextData: LiveData<Boolean> = _hasNextData

    /** 북마크 드라마 정보 전체 아이템 개수 */
    private val _totalCount: MutableLiveData<Int> = MutableLiveData(0)
    val totalCount: LiveData<Int> = _totalCount

    /** 북마크한 드라마 정보 LiveData */
    private val _dramasBookmarkLiveData: MutableLiveData<List<DramasBookmarkResponse>> =
        MutableLiveData(listOf())
    val dramasBookmarkLiveData: LiveData<List<DramasBookmarkResponse>> =
        _dramasBookmarkLiveData

    /** 북마크한 드라마 정보 가져오기 */
    fun getDramasBookmark(page: Int) {
        viewModelScope.launch {
            dramasBookmarkRepository.getDramasBookmarks(page, 20)
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { handleException(throwable = it) }
                .collect {
                    _hasNextData.value = it.next
                    _totalCount.value = it.totalCount
                    _dramasBookmarkLiveData.value = _dramasBookmarkLiveData.value?.plus(it.results)
                }
        }
    }

    /** 드라마 북마크 취소하기 */
    fun deleteBookmarkedDrama(slug: String, episode: String) {
        viewModelScope.launch {
            dramasBookmarkRepository.deleteDramasBookmark(slug, episode)
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { handleException(throwable = it) }
                .collect { resetData() }
        }
    }

    fun resetData() {
        _dramasBookmarkLiveData.value = listOf()
        getDramasBookmark(1)
    }
}