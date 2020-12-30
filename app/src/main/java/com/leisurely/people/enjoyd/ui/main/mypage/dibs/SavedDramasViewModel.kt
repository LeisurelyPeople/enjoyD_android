package com.leisurely.people.enjoyd.ui.main.mypage.dibs

import androidx.lifecycle.*
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

    /** 북마크한 드라마 정보 LiveData */
    private val _dramasBookmarkLiveData: MutableLiveData<List<DramasBookmarkResponse>> =
        MutableLiveData(listOf())
    val dramasBookmarkLiveData: LiveData<List<DramasBookmarkResponse>> =
        _dramasBookmarkLiveData

    /** 북마크한 드라마 정보 가져오기 */
    fun getDramasBookmark() {
        viewModelScope.launch {
            dramasBookmarkRepository.getDramasBookmarks()
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { handleException(throwable = it) }
                .collect { _dramasBookmarkLiveData.value = it }
        }
    }

    /** 드라마 북마크 취소하기 */
    fun deleteBookmarkedDrama(slug: String, episode: String) {
        viewModelScope.launch {
            dramasBookmarkRepository.deleteDramasBookmark(slug, episode)
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { handleException(throwable = it) }
                .collect { getDramasBookmark() }
        }
    }
}