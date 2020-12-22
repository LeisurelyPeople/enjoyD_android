package com.leisurely.people.enjoyd.data.remote.data.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * 사용자 정보 response model class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
@Parcelize
data class UserResponse(
    val name: String,
    val gender: Int,
    val birthday: String,
    val job: String,
    @SerializedName("signup_date")
    val signUpDate: String
) : Parcelable