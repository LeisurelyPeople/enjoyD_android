package com.leisurely.people.enjoyd.ui.onboarding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityUserInfoInputBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.login.model.SocialLogin
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
        parametersOf(intent.getParcelableExtra(Constant.EXTRA_SOCIAL_LOGIN_INFO))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvScreenTitle.text = getScreenTitleStringBuilder()
    }

    private fun getScreenTitleStringBuilder(): SpannedString {
        // TODO 폰트 추가 후 폰트 적용하기
        return buildSpannedString {
            append(SpannableString("모든 웹드라마").apply {
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_main_100, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
            append(SpannableString("를 한곳에서\n간편하게 즐기는 방법").apply {
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
        }
    }

    companion object {
        fun startActivity(context: Context, socialLogin: SocialLogin) {
            context.startActivity(Intent(context, UserInfoInputActivity::class.java).apply {
                putExtras(bundleOf(Constant.EXTRA_SOCIAL_LOGIN_INFO to socialLogin))
            })
        }
    }
}
