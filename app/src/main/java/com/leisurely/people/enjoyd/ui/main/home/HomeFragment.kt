package com.leisurely.people.enjoyd.ui.main.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentHomeBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeBannerListAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeDramasViewAllListAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeTagDramasListAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeTagsListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 홈탭 Fragment
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModel()

    /** 홈화면 배너 UI 부분을 담당하는 Adapter */
    private val homeBannerListAdapter by lazy {
        HomeBannerListAdapter()
    }

    /** 홈화면 태그 리스트 UI 부분을 담당하는 Adapter */
    private val homeTagsListAdapter by lazy {
        HomeTagsListAdapter {
            viewModel.getDramaItemsUsingTags(it.name)
        }
    }

    /** 홈화면 태그값을 이용해 검색된 드라마 리스트 UI 부분을 담당하는 Adapter */
    private val homeTagDramasListAdapter by lazy {
        HomeTagDramasListAdapter {
            // TODO 드라마 상세 페이지 연결 (담당자 : ricky)
        }
    }

    /** 홈화면 드라마 정보 전체보기 UI를 담당하는 Adapter */
    private val homeDramasViewAllListAdapter by lazy {
        HomeDramasViewAllListAdapter {
            Toast.makeText(requireContext(), "전체보기 클릭", Toast.LENGTH_SHORT).show()
            // TODO 전체 보기 화면으로 전환 (담당장 : Wayne)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHomeRV()
        observeViewModelLiveData()
    }

    private fun observeViewModelLiveData() {
        /** 드라마 배너 데이터 observe */
        viewModel.dramasBannerData.observe(viewLifecycleOwner, Observer {
            homeBannerListAdapter.submitList(it)
        })

        /** 드라마 태그 데이터 observe */
        viewModel.dramasTagsInfo.observe(viewLifecycleOwner, Observer {
            homeTagsListAdapter.submitList(it)
        })

        /** 드라마 태그값을 이용한 드라마 정보 관련 observe */
        viewModel.dramaItems.observe(viewLifecycleOwner, Observer {
            homeTagDramasListAdapter.tag = viewModel.tag.value ?: ""
            homeTagDramasListAdapter.submitList(it)
        })

        /** 드라마 전체보기 버튼을 활성화 시킬지에 대한 observe */
        viewModel.dramaViewAllItem.observe(viewLifecycleOwner, Observer {
            homeDramasViewAllListAdapter.tag = viewModel.tag.value ?: ""
            homeDramasViewAllListAdapter.submitList(it)
        })
    }

    private fun setHomeRV() {
        binding.rvHome.run {
            adapter = ConcatAdapter(
                homeBannerListAdapter,
                homeTagsListAdapter,
                homeTagDramasListAdapter,
                homeDramasViewAllListAdapter
            )
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    /** ConcatAdapter 사용 시 ViewHolder 객체 비교를 통해 spacing 값을 설정 */
                    if (parent.getChildViewHolder(view) is HomeTagsListAdapter.HomeTagsVH) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_32dp)
                    }
                    if (parent.getChildViewHolder(view)
                                is HomeTagDramasListAdapter.HomeTagDramasVH
                    ) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_20dp)
                    }

                    if (parent.getChildViewHolder(view)
                                is HomeDramasViewAllListAdapter.HomeDramasViewMoreVH
                    ) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_4dp)
                        outRect.bottom =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_80dp)
                        outRect.left =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.right =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)

                    }
                }
            })
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}