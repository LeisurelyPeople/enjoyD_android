package com.leisurely.people.enjoyd.ui.onboarding

import android.util.Log
import com.leisurely.people.enjoyd.ui.base.BaseViewModel
import com.leisurely.people.enjoyd.ui.login.model.SocialLogin

/**
 * UserInfoInputActivity ViewModel
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

class UserInfoInputViewModel(private val socialLogin: SocialLogin) : BaseViewModel() {

    init {
        // TODO 회원가입 로직
        Log.d("test1414", "성공")
    }
}