package com.leisurely.people.enjoyd.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.os.bundleOf
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.data.remote.data.request.SignUpRequest
import com.leisurely.people.enjoyd.databinding.ActivityUserInfoInputBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.util.Constant
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 사용자 정보 입력 화면 (회원가입)
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

class UserInfoInputActivity :
    BaseActivity<ActivityUserInfoInputBinding, UserInfoInputViewModel>(R.layout.activity_user_info_input) {

    override val viewModel: UserInfoInputViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(Constant.EXTRA_SIGN_UP_REQUEST))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun startActivity(context: Context, signUpRequest: SignUpRequest) {
            context.startActivity(Intent(context, UserInfoInputActivity::class.java).apply {
                putExtras(bundleOf(Constant.EXTRA_SIGN_UP_REQUEST to signUpRequest))
            })
        }
    }
}
