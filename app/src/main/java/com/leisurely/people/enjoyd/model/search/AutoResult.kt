package com.leisurely.people.enjoyd.model.search

/**
 * 타이핑 중에 자동으로 나오는 검색 결과에 대한 데이터 클래스
 * 기획 단계에서 없어진 내용이므로 Deprecated 처리함
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.20
 */
@Deprecated(message = "기획 단계에서 없어진 내용이므로 Deprecated 처리함")
data class AutoResult(
    var isCategory: Boolean,
    var title: String
)
