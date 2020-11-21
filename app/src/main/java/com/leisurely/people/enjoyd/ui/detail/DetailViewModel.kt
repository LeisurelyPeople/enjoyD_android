package com.leisurely.people.enjoyd.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leisurely.people.enjoyd.data.remote.data.response.*
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

    // 상단 actionBar 제목
    private val _title: MutableLiveData<String> = MutableLiveData()
    var title: LiveData<String> = _title

    // 감독
    private val _writer: MutableLiveData<String> = MutableLiveData()
    var writer: LiveData<String> = _writer

    // 포스터 URL
    private val _poster: MutableLiveData<String> = MutableLiveData()
    var poster: LiveData<String> = _poster

    // 줄거리
    private val _summary: MutableLiveData<String> = MutableLiveData()
    var summary: LiveData<String> = _summary

    // 평점
    private val _avgRating: MutableLiveData<String> = MutableLiveData()
    var avgRating: LiveData<String> = _avgRating

    // 다른 회차 둘러보기 목록
    private val _others: MutableLiveData<List<DramasSlugEpisodesResponseItem>> = MutableLiveData()
    var others: LiveData<List<DramasSlugEpisodesResponseItem>> = _others

    // 연관 드라마 목록
    private val _rels: MutableLiveData<List<DramasSlugRelatedSearchResponseItem>> =
        MutableLiveData()
    var rels: LiveData<List<DramasSlugRelatedSearchResponseItem>> = _rels

    /** 뒤로가기 버튼을 동작시키기 위한 메소드 */
    fun onClickBackScreen() {
        _startBackScreen.value = null
    }

    init {
        _title.value = ""

        _others.value = listOf()

        _rels.value = listOf()

    }

    // 상세 화면에서 초기에 처리해주어야 할 내용을 처리한다.
    fun init(dramasSlug: String) {
        viewModelScope.launch(vmDefaultExceptionHandler(CoroutineKey.DETAIL_GET_DRAMAS_SLUG)) {
            // dramasSlug 값을 기반으로 드라마 상세 데이터를 가져온 후 화면을 세팅한다.
            dramaRepository.getDramasSlug(dramasSlug)
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSlugGetResponse>() {
                    override fun onSuccess(detailDrama: DramasSlugGetResponse) {
                        _title.value = detailDrama.title
                        _writer.value = detailDrama.writer
                        _poster.value = detailDrama.poster
                        _summary.value = detailDrama.summary
                        _avgRating.value = "${detailDrama.avgRating}"
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _title.value = "일치하는 데이터를 찾을 수 없습니다."
                        _writer.value = ""
                        _poster.value = ""
                        _summary.value = ""
                        _avgRating.value = ""
                    }
                })

            // 다른 회차 둘러보기 목록을 보여주기 위한 데이터를 세팅한다.
            dramaRepository.getDramasSlugEpisodes(dramasSlug)
                .applySingleSchedulers()
                .subscribeWith(object : DisposableSingleObserver<DramasSlugEpisodesResponse>() {
                    override fun onSuccess(episodes: DramasSlugEpisodesResponse) {
                        _others.value = episodes.results
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _others.value = listOf()
                    }
                })

            // 다른 회차 둘러보기 목록을 보여주기 위한 데이터를 세팅한다.
            dramaRepository.getDramasSlugRelatedSearch(dramasSlug)
                .applySingleSchedulers()
                .subscribeWith(object :
                    DisposableSingleObserver<DramasSlugRelatedSearchResponse>() {
                    override fun onSuccess(episodes: DramasSlugRelatedSearchResponse) {
                        _rels.value = episodes.results
                    }

                    override fun onError(e: Throwable) {
                        super.onError(e)
                        _rels.value = listOf()
                    }
                })
        }
    }
}
