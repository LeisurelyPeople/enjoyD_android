package com.leisurely.people.enjoyd.data.remote.data.response

import com.google.gson.annotations.SerializedName

/**
 * 유저 정보 결과값
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
data class UserTokenResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expire_date")
    val expireDate: String
)