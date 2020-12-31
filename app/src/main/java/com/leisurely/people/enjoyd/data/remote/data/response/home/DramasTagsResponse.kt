package com.leisurely.people.enjoyd.data.remote.data.response.home

import com.leisurely.people.enjoyd.model.DramasTagsModel

/**
 * 홈화면에 있는 태그들을 담고 있는 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
data class DramasTagsResponse(val name: String) {
    fun toDramaTagsModel() = DramasTagsModel(name, false)
}