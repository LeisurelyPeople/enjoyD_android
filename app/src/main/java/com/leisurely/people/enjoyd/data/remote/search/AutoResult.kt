package com.leisurely.people.enjoyd.data.remote.search

/**
 * 타이핑 중에 자동으로 나오는 검색 결과에 대한 데이터 클래스
 * TODO 데이터 구성이 어떻게 되는지 알 수 없어 기초 이름만 넣음
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.20
 */
data class AutoResult(
    var isCategory: Boolean,
    var title: String
)
