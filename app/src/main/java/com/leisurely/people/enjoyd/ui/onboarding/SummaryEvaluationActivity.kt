package com.leisurely.people.enjoyd.ui.onboarding

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivitySummaryEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.ui.main.MainActivity
import com.leisurely.people.enjoyd.util.CustomItemDecoration
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 온보딩 과정중의 평가하기 액티비티
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.09
 */
class SummaryEvaluationActivity :
    BaseActivity<ActivitySummaryEvaluationBinding, SummaryEvaluationViewModel>(
        R.layout.activity_summary_evaluation
    ) {

    override val viewModel: SummaryEvaluationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSummaryEvaluationRV()
        binding.tvScreenTitle.text = getScreenTitleStringBuilder()
        viewModel.getDramaEvaluationItems()

        viewModel.startMain.observe(this, Observer {
            onStartEnjoyDService()
        })
    }

    override fun onBackPressed() {
        onStartEnjoyDService()
    }

    private fun setSummaryEvaluationRV() {
        binding.rvEvaluationDrama.run {
            adapter = SummaryEvaluationRVAdapter()
            addItemDecoration(object : CustomItemDecoration() {
                override fun setSpacingForDirection(
                    outRect: Rect,
                    layoutManager: RecyclerView.LayoutManager?,
                    position: Int,
                    itemCount: Int
                ) {
                    outRect.bottom = if (position != itemCount - 1) {
                        resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_24dp)
                    } else {
                        0
                    }
                }

            })
        }
    }

    private fun getScreenTitleStringBuilder(): SpannedString {
        val typefaceNotoSansBold =
            ResourcesCompat.getFont(this, R.font.noto_sans_kr_bold)
        val typefaceNotoSansDemiLight =
            ResourcesCompat.getFont(this, R.font.noto_sans_kr_demi_light)

        return buildSpannedString {
            append(SpannableString("감상한 웹드라마").apply {
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
            append(SpannableString(" 작품을\n").apply {
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
            append(SpannableString("평가").apply {
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
            append(SpannableString("해주세요!").apply {
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

    private fun onStartEnjoyDService() {
        val intent = MainActivity.getIntent(this).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        startActivity(intent)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, SummaryEvaluationActivity::class.java)
        }
    }
}