package com.leisurely.people.enjoyd.model.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 소셜 로그인에 대한 사용자 정보 모델 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.08.21
 */
@Parcelize
data class SocialLoginModel(
    val socialId: Long?,
    val name: String?,
    val gender: Int?
) : Parcelable