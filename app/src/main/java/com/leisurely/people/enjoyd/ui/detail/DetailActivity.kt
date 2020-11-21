package com.leisurely.people.enjoyd.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityDetailBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
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

        val otherAdapter = DetailOtherAdapter()
        rv_others.adapter = otherAdapter

        val relAdapter = DetailRelAdapter()
        rv_rels.adapter = relAdapter

        viewModel.init("manjjijnamnyeo")

        viewModel.startBackScreen.observe(this, Observer {
            finish()
        })
    }
}