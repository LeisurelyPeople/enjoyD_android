package com.leisurely.people.enjoyd.ui.onboarding

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.core.text.buildSpannedString
import com.leisurely.people.enjoyd.R
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.databinding.ActivityUserInfoInputBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.model.login.SocialLoginModel
import com.leisurely.people.enjoyd.util.Constant
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan
import com.leisurely.people.enjoyd.util.ext.formatToViewDate
import com.leisurely.people.enjoyd.util.time.TimePoint
import com.leisurely.people.enjoyd.util.time.days
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

/**
 * 사용자 정보 입력 화면 (회원가입)
 *
 * @author Wayne
 * @since v1.0.0 / 2020.07.20
 */

class UserInfoInputActivity :
    BaseActivity<ActivityUserInfoInputBinding, UserInfoInputViewModel>(
        R.layout.activity_user_info_input
    ) {

    override val viewModel: UserInfoInputViewModel by viewModel {
        parametersOf(intent.getParcelableExtra(Constant.EXTRA_SOCIAL_LOGIN_INFO))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.tvScreenTitle.text = getScreenTitleStringBuilder()

        viewModel.startBirthDayPicker.observe(this, Observer {
            openDatePicker()
        })

        viewModel.startBackScreen.observe(this, Observer {
            finish()
        })

        viewModel.startSummaryEvaluation.observe(this, Observer {
            startActivity(SummaryEvaluationActivity.getIntent(this))
            finish()
        })
    }

    private fun getScreenTitleStringBuilder(): SpannedString {
        val typefaceNotoSansBold =
            ResourcesCompat.getFont(this, R.font.noto_sans_kr_bold)
        val typefaceNotoSansDemiLight =
            ResourcesCompat.getFont(this, R.font.noto_sans_kr_demi_light)

        return buildSpannedString {
            append(SpannableString("모든 웹드라마").apply {
                setSpan(
                    CustomTypefaceSpan(typefaceNotoSansBold),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_main_100, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
            append(SpannableString("를 ").apply {
                setSpan(
                    CustomTypefaceSpan(typefaceNotoSansDemiLight),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
            append(SpannableString("한곳에서\n간편하게").apply {
                setSpan(
                    CustomTypefaceSpan(typefaceNotoSansBold),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
            append(SpannableString(" 즐기는 방법").apply {
                setSpan(
                    CustomTypefaceSpan(typefaceNotoSansDemiLight),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                setSpan(
                    ForegroundColorSpan(resources.getColor(R.color.color_grey_0, theme)),
                    0,
                    this.length,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            })
        }
    }

    private fun openDatePicker() {
        val dateTimePoint = TimePoint.now
        DatePickerDialog(
            this,
            R.style.UserBirthDayDatePickerStyle,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val userBirthCalendar = TimePoint(year, month, dayOfMonth)
                viewModel.setUserBirthDayValue(userBirthCalendar.formatToViewDate())
            },
            dateTimePoint.year,
            dateTimePoint.month,
            dateTimePoint.day
        ).apply {
            datePicker.minDate = TimePoint(1970, 1, 1).unixMillis
            datePicker.maxDate = dateTimePoint.unixMillis
        }.show()
    }

    companion object {
        fun startActivity(context: Context, socialLoginModel: SocialLoginModel) {
            context.startActivity(Intent(context, UserInfoInputActivity::class.java).apply {
                putExtras(bundleOf(Constant.EXTRA_SOCIAL_LOGIN_INFO to socialLoginModel))
            })
        }
    }
}
