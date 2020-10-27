package com.leisurely.people.enjoyd.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchGetResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponseItem
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugGetResponse
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugResponseItem
import com.leisurely.people.enjoyd.data.repository.DramaRepository
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.util.coroutine.CoroutineKey
import com.leisurely.people.enjoyd.util.ext.applySingleSchedulers
import com.leisurely.people.enjoyd.util.lifecycle.LiveEvent
import com.leisurely.people.enjoyd.util.observer.DisposableSingleObserver
import kotlinx.coroutines.launch

/**
 * 상세 화면의 ViewModel
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.28
 */
class DetailViewModel(private val dramaRepository: DramaRepository) : BaseViewModel() {
    /** 뒤로가기 버튼을 동작시키기 위한 LiveData */
    private val _startBackScreen: LiveEvent<Unit> = LiveEvent()
    val startBackScreen: LiveEvent<Unit> = _startBackScreen

    // 상세 화면에 보여줄 드라마 아이템 정보
    private val _detailDrama: MutableLiveData<DramasSlugGetResponse> = MutableLiveData()
    var detailDrama: LiveData<DramasSlugGetResponse> = _detailDrama

    // 상단 actionBar 제목
    private val _title: MutableLiveData<String> = MutableLiveData()
    var title: LiveData<String> = _title

    // 다른 회차 둘러보기 목록
    private val _others: MutableLiveData<List<DramasSlugResponseItem>> = MutableLiveData()
    var others: LiveData<List<DramasSlugResponseItem>> = _others

    // 연관 드라마 목록
    private val _recos: MutableLiveData<List<DramasSearchResponseItem>> = MutableLiveData()
    var recos: LiveData<List<DramasSearchResponseItem>> = _recos

    /** 뒤로가기 버튼을 동작시키기 위한 메소드 */
    fun onClickBackScreen() {
        _startBackScreen.value = null
    }

    init {
        _detailDrama.value = null

        _title.value = ""

        _others.value = listOf()

        _recos.value = listOf()

    }

    // 상세 화면에서 초기에 처리해주어야 할 내용을 처리한다.
    fun init(dramasSlug: String) {
        viewModelScope.launch(vmDefaultExceptionHandler(CoroutineKey.DETAIL_GET_DRAMAS_SLUG)) {
            // dramasSlug 값을 기반으로 드라마 상세 데이터를 가져온 후 화면을 세팅한다.
            dramaRepository.getDramasSlug(dramasSlug)
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSlugGetResponse>() {
                    override fun onSuccess(detailDrama: DramasSlugGetResponse) {
                        _detailDrama.value = detailDrama

                        _title.value = _detailDrama.value?.title
                        _others.value = _detailDrama.value?.dramas
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _detailDrama.value = null

                        _title.value = "일치하는 데이터를 찾을 수 없습니다."
                        _others.value = listOf()
                    }
                })

            // 연관 드라마 목록을 보여주기 위한 데이터를 세팅한다.
            dramaRepository.getDramasSearch("", "avg_rating")
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSearchGetResponse>() {
                    override fun onSuccess(searchDramas: DramasSearchGetResponse) {
                        _recos.value = searchDramas
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _recos.value = listOf()
                    }
                })
        }
    }
}
