package com.leisurely.people.enjoyd.util

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

        // SharedPreferences
        const val PREF_NAME = "ENJOY_D_PREF"
        const val PREF_USER_TOKEN = "PREF_USER_TOKEN"

        // Result Code (onActivityResult)
        const val RESULT_OK = 1
        const val RESULT_CANCEL = 0
        const val RESULT_ERROR = -1
    }
}