package com.leisurely.people.enjoyd.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityDetailBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_VIDEO_ID
import kotlinx.android.synthetic.main.layout_detail_other_rel.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 웹드라마 상세 정보를 보여주는 화면
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.28
 */
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    R.layout.activity_detail
) {
    override val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
    }

    /** UI 초기 설정을 진행한다. */
    private fun initLayout() {
        val otherAdapter = DetailOtherAdapter(viewModel.onBookmarkAction)
        rv_others.adapter = otherAdapter
        rv_others.isNestedScrollingEnabled = false

        val relAdapter = DetailRelAdapter()
        rv_rels.adapter = relAdapter
        rv_rels.isNestedScrollingEnabled = false

        val videoId = intent.getStringExtra(EXTRA_VIDEO_ID) ?: ""

        viewModel.init(videoId)

        viewModel.startBackScreen.observe(this, Observer { finish() })
    }
}
