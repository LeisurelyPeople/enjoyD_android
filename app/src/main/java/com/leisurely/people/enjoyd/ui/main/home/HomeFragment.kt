package com.leisurely.people.enjoyd.ui.main.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentHomeBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeBannerRVAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeTagsRVAdapter
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

    private val homeTagsRVAdapter by lazy {
        HomeTagsRVAdapter {
            // TODO 선택된 태그에 의한 드라마 정보 가져오기 작업
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeRV()
        observeViewModelLiveData()
    }

    private fun observeViewModelLiveData() {
        viewModel.dramasBannerData.observe(viewLifecycleOwner, Observer {
            homeBannerRVAdapter.setHomeBannerData(it)
        })

        viewModel.dramasTagsInfo.observe(viewLifecycleOwner, Observer {
            homeTagsRVAdapter.setDramaTagItems(it.results)
        })
    }

    private fun setHomeRV() {
        binding.rvHome.run {
            adapter = ConcatAdapter(homeBannerRVAdapter, homeTagsRVAdapter)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    if (parent.getChildViewHolder(view) is HomeTagsRVAdapter.HomeTagsVH) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_32dp)
                    }
                }
            })
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}