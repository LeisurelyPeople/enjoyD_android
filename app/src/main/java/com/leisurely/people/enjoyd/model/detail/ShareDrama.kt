package com.leisurely.people.enjoyd.model.detail

import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugEpisodesResponseItem
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugRelatedSearchResponseItem

/**
 * 공유할 때 사용하는 드라마 데이터 클래스
 *
 * @param slug 영화 id
 * @param title 영화 제목
 * @param writer 감독
 * @param poster 포스터 URL
 * @param summary 줄거리
 * @param avgRating 평점
 * @param others 다른 회차 둘러보기 목록
 * @param rels 연관 드라마 목록
 *
 * @author ricky
 * @since v1.0.0 / 2021.01.12
 */
data class ShareDrama(
    val slug: String,
    val title: String,
    val writer: String,
    val poster: String,
    val summary: String,
    val avgRating: String,
    val others: List<DramasSlugEpisodesResponseItem>,
    val rels: List<DramasSlugRelatedSearchResponseItem>
)
