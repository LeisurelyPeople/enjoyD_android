package com.leisurely.people.enjoyd.ui.evaluation

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.ViewPropertyAnimator
import android.view.WindowManager
import android.view.animation.AnimationUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.util.TextDecoration.ColorUtil
import com.leisurely.people.enjoyd.util.TextDecoration.StyledText
import com.leisurely.people.enjoyd.util.TextDecoration.TextDecorationUtil
import com.leisurely.people.enjoyd.util.ext.submitList
import kotlinx.android.synthetic.main.activity_evaluation.*

/**
 * 평가하기 액티비티
 * 추후 Fragment 나 Layout 으로 변환될 가능성 있음
 *
 * @author ricky
 * @since v1.0.0 / 2020.06.30
 */
class EvaluationActivity : BaseActivity<ActivityEvaluationBinding, EvaluationViewModel>(
    layoutRes = R.layout.activity_evaluation
) {
    override val viewModel: EvaluationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 평가하기 타이틀 텍스트 generate
        tv_evaluation_description.text = TextDecorationUtil.generateStyledText(
            listOf(
                StyledText(
                    "감상한 웹드라마 ",
                    color = ColorUtil.getColorStringToInt("#ff00ff", "#ffffff"),
                    isBold = true
                ),
                StyledText(
                    "작품을 \n",
                    color = ColorUtil.getColorStringToInt("#ffffff", "#ffffff")
                ),
                StyledText(
                    "평가",
                    color = ColorUtil.getColorStringToInt("#ffffff", "#ffffff"),
                    isBold = true
                ),
                StyledText(
                    "해주세요!",
                    color = ColorUtil.getColorStringToInt("#ffffff", "#ffffff")
                )
            )
        )

        rv_evaluations.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val adapter = EvaluationListAdapter()
        rv_evaluations.adapter = adapter

        rv_evaluations.submitList(listOf("a", "b", "c", "d", "e", "f"))

        // 각 탭 아이템 클릭 시
        tab_layout_home.setOnClickListener {
            startUnderBarAnimation(0)
            rv_evaluations.submitList(listOf("a", "b", "c"))
        }
        tab_layout_center.setOnClickListener {
            startUnderBarAnimation(1)
            rv_evaluations.submitList(listOf("a", "b", "c", "d"))
        }
        tab_layout_mypage.setOnClickListener {
            startUnderBarAnimation(2)
            rv_evaluations.submitList(listOf("a", "b", "c", "d", "e"))
        }
    }

    /** 하단 바 슬라이드 애니메이션을 시작한다. */
    private fun startUnderBarAnimation(position: Int) {
        val animation: ViewPropertyAnimator = tab_under_bar.animate()
        val screenX = tab_under_bar_layout.width / 3

        animation.duration = 250
        animation.interpolator = AnimationUtils.loadInterpolator(
            this, android.R.anim.accelerate_decelerate_interpolator
        )

        when (position) {
            0 -> animation.translationX(0f)
            1 -> animation.translationX(screenX.toFloat())
            2 -> animation.translationX((2 * screenX).toFloat())
        }
        animation.start()
    }

    /**
     * Screen Size
     */
    fun screenSize(context: Context): Point {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val size = Point()
        display.getSize(size)
        return size
    }
}
