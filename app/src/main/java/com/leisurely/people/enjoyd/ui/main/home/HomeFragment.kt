package com.leisurely.people.enjoyd.ui.main.home

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentHomeBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.common.adapter.DramaListAdapter
import com.leisurely.people.enjoyd.ui.common.adapter.DramaTagsListAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 홈탭 Fragment
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: HomeViewModel by viewModel()

    /** 홈화면 배너 UI 부분을 담당하는 Adapter */
    private val homeBannerListAdapter by lazy {
        HomeBannerListAdapter()
    }

    /** 홈화면 시청중인 드라마 리스트 타이틀 UI를 보여주기 위한 Adapter */
    private val homeDramasWatchingTitleListAdapter by lazy {
        HomeDramasWatchingTitleListAdapter()
    }

    /** 홈화면 시청중인 드라마 리스트 UI를 보며주기 위한 Parent Wrapper Adapter */
    private val homeParentDramasWatchingRVAdapter by lazy {
        HomeParentDramasWatchingRVAdapter(homeChildDramasWatchingListAdapter)
    }

    /** 홈화면 시청중인 드라마 리스트 UI를 보여주기 위한 Child Adapter */
    private val homeChildDramasWatchingListAdapter by lazy {
        HomeChildDramasWatchingListAdapter {
            // TODO 유튜브 플레이어로 바로 열어주기 (담당자 : ricky)
            Toast.makeText(requireContext(), it.episodeTitle, Toast.LENGTH_SHORT).show()
        }
    }

    /** 홈화면 태그 리스트 UI를 보여주기 위한 Parent Wrapper Adapter */
    private val homeParentTagsRVAdapter by lazy {
        HomeParentTagsRVAdapter(homeChildTagsListAdapter)
    }

    /** 홈화면 태그 리스트 UI를 보여주기 위한 child Adapter */
    private val homeChildTagsListAdapter by lazy {
        DramaTagsListAdapter {
            // 태그를 클릭했을 경우 새로운 드라마 데이터들을 가져옴.
            viewModel.onTagClick(it)
        }
    }

    /** 홈화면 드라마 리스트 타이틀 UI를 보여주기 위한 Adapter */
    private val homeDramasTitleListAdapter by lazy {
        HomeDramasTitleListAdapter()
    }

    /** 홈화면 드라마 리스트 UI를 보여주기 위한 Parent Wrapper Adapter */
    private val homeParentDramasRVAdapter by lazy {
        HomeParentDramasRVAdapter(homeChildDramaListAdapter)
    }

    /** 홈화면 드라마 리스트 UI를 보여주기 위한 child Adapter */
    private val homeChildDramaListAdapter by lazy {
        DramaListAdapter {
            // TODO 드라마 상세 화면을 연결 작업 하는 곳 (담당자 : ricky)
            Toast.makeText(requireContext(), it.poster, Toast.LENGTH_SHORT).show()
        }
    }

    /** 홈화면 드라마 정보 전체보기 UI를 담당하는 Adapter */
    private val homeDramasViewMoreListAdapter by lazy {
        HomeDramasViewMoreListAdapter {
            val page = viewModel.page.value?.plus(1) ?: return@HomeDramasViewMoreListAdapter
            val tag = viewModel.selectedTag.value ?: return@HomeDramasViewMoreListAdapter
            viewModel.getDramaItemsUsingTags(page, tag)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeRefreshLayout()
        setHomeRV()
        observeViewModelLiveData()
        viewModel.getHomeData()
    }

    override fun onRefresh() {
        viewModel.getHomeData()
    }

    private fun setSwipeRefreshLayout() {
        binding.srlHome.setOnRefreshListener(this)
    }

    private fun observeViewModelLiveData() {
        /** 로딩 observe */
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.srlHome.isRefreshing = it
        })

        /** 검색 화면 이동 여부 observer */
        viewModel.startSearchPage.observe(viewLifecycleOwner, Observer {
            // TODO 검색 화면으로 연결 작업 하는 곳 (담당장 : ricky)
        })

        /** 드라마 배너 데이터 observe */
        viewModel.dramasBannerData.observe(viewLifecycleOwner, Observer {
            homeBannerListAdapter.submitList(it)
        })

        /** 시청중인 드라마 데이터 observe */
        viewModel.dramasWatchingData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) homeDramasWatchingTitleListAdapter.submitList(listOf(Unit))
            homeChildDramasWatchingListAdapter.submitList(it)
        })

        /** 드라마 태그 데이터 observe */
        viewModel.dramasTagsItem.observe(viewLifecycleOwner, Observer {
            homeChildTagsListAdapter.submitList(it)
        })

        /** 드라마 태그값을 이용한 드라마 정보 관련 observe */
        viewModel.dramaItems.observe(viewLifecycleOwner, Observer {
            // 드라마 리스트가 비어있지 않을 경우 타이틀 UI 보여주기
            if (it.isNotEmpty()) {
                homeDramasTitleListAdapter.submitList(listOf(viewModel.selectedTag.value))
            }
            homeChildDramaListAdapter.submitList(it)
        })

        /** 드라마 더보기 버튼을 활성화 시킬지에 대한 observe */
        viewModel.existsMoreDramaItems.observe(viewLifecycleOwner, Observer {
            homeDramasViewMoreListAdapter.tag = viewModel.selectedTag.value ?: ""
            homeDramasViewMoreListAdapter.submitList(it)
        })
    }

    private fun setHomeRV() {
        binding.rvHome.run {
            adapter = ConcatAdapter(
                homeBannerListAdapter,
                homeDramasWatchingTitleListAdapter,
                homeParentDramasWatchingRVAdapter,
                homeParentTagsRVAdapter,
                homeDramasTitleListAdapter,
                homeParentDramasRVAdapter,
                homeDramasViewMoreListAdapter
            )
            /** ConcatAdapter 사용 시 ViewHolder 객체 비교를 통해 spacing 값을 설정 */
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    /** 시청중인 드라마 리스트 타이틀 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeDramasWatchingTitleListAdapter.HomeDramasWatchingTitleVH
                    ) {
                        outRect.left =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.right =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_32dp)
                    }

                    /** 시청중인 드라마 리스트 부모 Wrapper 어댑터에 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeParentDramasWatchingRVAdapter.HomeParentDramasWatchingVH
                    ) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)
                    }

                    /** 드라마 태그 리스트 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeParentTagsRVAdapter.HomeParentTagsVH
                    ) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_32dp)
                    }

                    /** 드라마 리스트 타이틀 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeDramasTitleListAdapter.HomeDramasTitleVH
                    ) {
                        outRect.left =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.right =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_20dp)
                    }

                    /** 드라마 리스트 부모 Wrapper 어댑터에 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeParentDramasRVAdapter.HomeParentDramasVH
                    ) {
                        outRect.top =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)
                    }

                    /** 드라마 더보기 버튼 spacing 값 설정 */
                    if (parent.getChildViewHolder(view)
                                is HomeDramasViewMoreListAdapter.HomeDramasViewMoreVH
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