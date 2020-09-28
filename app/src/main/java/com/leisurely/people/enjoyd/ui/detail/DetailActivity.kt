package com.leisurely.people.enjoyd.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ActivityDetailBinding
import com.leisurely.people.enjoyd.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 웹드라마 상세 정보를 보여주는 화면
 *
 * @author ricky
 * @since v1.0.0 / 2020.09.28
 */
class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>(
    layoutRes = R.layout.activity_detail
) {
    override val viewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.startBackScreen.observe(this, Observer {
            finish()
        })
    }
}