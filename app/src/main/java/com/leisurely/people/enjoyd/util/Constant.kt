package com.leisurely.people.enjoyd.util

import com.leisurely.people.enjoyd.BuildConfig

/**
 * Intent, Arguments, ACTION_VIEW 등의 키값을 설정하는 곳
 *
 * Intent -> EXTRA_XXX
 * Fragment Arguments -> ARGUMENT_XXX
 * SharedPreferences -> PREF_XXX
 * ACTION_VIEW -> ACTION_VIEW_XXX
 * onActivityResult request code -> REQUEST_CODE_XXX
 * onActivityResult result code -> RESULT_CODE_XXX
 * ...
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */
interface Constant {

    companion object {

        // Intent
        const val EXTRA_SIGN_UP_REQUEST = "EXTRA_SIGN_UP_REQUEST"
        const val EXTRA_SOCIAL_LOGIN_INFO = "EXTRA_SOCIAL_LOGIN_INFO"
        const val EXTRA_VIDEO_ID = "EXTRA_VIDEO_ID"
        const val EXTRA_KAKAO_LINK_VIDEO_ID = "EXTRA_KAKAO_LINK_VIDEO_ID"

        // SharedPreferences
        const val PREF_NAME = "ENJOY_D_PREF"
        const val PREF_USER_TOKEN = "PREF_USER_TOKEN"
        const val PREF_RECENT_SEARCH_WORD = "PREF_RECENT_SEARCH_WORD"

        // Result Code (onActivityResult)
        const val RESULT_OK = 1
        const val RESULT_CANCEL = 0
        const val RESULT_ERROR = -1

        // Fragment Tag
        const val FRAGMENT_TAG_PROGRESS_BAR_DIALOG = "FRAGMENT_TAG_PROGRESS_BAR_DIALOG"
        
        // 카카오에서 쓰이는 상수값
        const val KAKAO_LINK_SLUG = "KAKAO_LINK_SLUG"

        // 기타
        // 지금 실행되는 코드가 Unit Test 인지 확인하는 flag
        var isUnitTest = false

        // 더미 유저의 accessToken
        const val dummyUserAccessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9" +
                ".eyJ0eXBlIjoxLCJleHAiOjE2MDA3NDA3OTU2NjcsInVzZXJfbmFtZSI6InRlc3QifQ" +
                ".MN9dAK-easXxnJrkKgK_sPq7Uln2IdOY7PivM5EI6uU"

        // 앱 버전 정보
        val appVersion: String by lazy { BuildConfig.VERSION_NAME }
    }
}
