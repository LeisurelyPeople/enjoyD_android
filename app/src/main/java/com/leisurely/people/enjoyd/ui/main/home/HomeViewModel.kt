package com.leisurely.people.enjoyd.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.data.repository.DramasBannerRepository
import com.leisurely.people.enjoyd.data.repository.DramasTagsRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel

/**
 * 홈탭 관련 ViewModel class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeViewModel(
    private val dramasBannerRepository: DramasBannerRepository,
    private val dramasTagsRepository: DramasTagsRepository
) : BaseViewModel() {

    val dramasBannerData: LiveData<DramasBannerResponse> = liveData {
        emit(dramasBannerRepository.getDramasBanner())
    }

    val dramasTagsInfo: LiveData<PagingResponse<DramasTagsResponse>> = liveData {
        emit(dramasTagsRepository.getDramasTags())
    }
}