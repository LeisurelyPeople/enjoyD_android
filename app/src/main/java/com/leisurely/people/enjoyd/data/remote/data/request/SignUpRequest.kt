package com.leisurely.people.enjoyd.data.remote.data.request

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 회원가입 요청 request model class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

@Parcelize
data class SignUpRequest(
    @SerializedName("social_id")
    val socialId: Long,
    val name: String,
    val job: String,
    val birthday: String,
    val gender: Int
) : Parcelable {
    constructor(socialId: Long, name: String?, gender: Int?) : this(
        socialId,
        name ?: "",
        "",
        "",
        gender ?: 0
    )
}