package com.leisurely.people.enjoyd.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.MergeAdapter
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentHomeBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 홈탭 Fragment
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    private val homeBannerRVAdapter by lazy {
        HomeBannerRVAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeRV()

        viewModel.dramasBannerData.observe(viewLifecycleOwner, Observer {
            homeBannerRVAdapter.setHomeBannerData(it)
        })

        viewModel.dramasTagsInfo.observe(viewLifecycleOwner, Observer {
            // TODO 태그 정보 Recyclerview 연결 작업
        })
    }

    private fun setHomeRV() {
        binding.rvHome.run {
            adapter = MergeAdapter(homeBannerRVAdapter)
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}