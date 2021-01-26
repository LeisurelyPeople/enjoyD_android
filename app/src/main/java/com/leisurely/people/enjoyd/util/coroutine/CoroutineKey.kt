package com.leisurely.people.enjoyd.util.coroutine

// @formatter:off
/**
 * 코루틴 비즈니스 로직에서 사용하는 throwable 메시지 이름 관리
 * 변수명 작성 기준 : 화면명_행동
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.27
 */
object CoroutineKey {
    const val SEARCH_CLICK_SEARCH_BTN = "SEARCH_CLICK_SEARCH_BTN"   // 검색 화면에서, 서치 버튼 (돋보기 버튼)을 누른다.

    const val DETAIL_GET_DRAMAS_SLUG = "DETAIL_GET_DRAMAS_SLUG"   // 상세 화면에서, 드라마 상세 아이템 정보를 가져온다.
}
// @formatter:on
