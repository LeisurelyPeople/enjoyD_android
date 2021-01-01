package com.leisurely.people.enjoyd.ui.main.mypage.dibs

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentSavedDramasBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.detail.DetailActivity
import com.leisurely.people.enjoyd.ui.main.mypage.MyPageViewModel
import com.leisurely.people.enjoyd.ui.main.mypage.dibs.adapter.SavedDramasListAdapter
import com.leisurely.people.enjoyd.util.Constant.Companion.EXTRA_VIDEO_ID
import com.leisurely.people.enjoyd.util.CustomItemDecoration
import com.leisurely.people.enjoyd.util.EndlessRVScrollListener
import io.reactivex.disposables.CompositeDisposable
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 찜한 드라마 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.22
 */
class SavedDramasFragment :
    BaseFragment<FragmentSavedDramasBinding, SavedDramasViewModel>(R.layout.fragment_saved_dramas),
    SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: SavedDramasViewModel by viewModel()

    private val parentViewModel by lazy {
        requireParentFragment().getViewModel<MyPageViewModel>()
    }

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeRefreshLayout()
        setSavedDramasRV()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.resetData()
    }

    override fun onRefresh() {
        viewModel.resetData()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun setSwipeRefreshLayout() {
        binding.srlEvaluation.setOnRefreshListener(this)
    }

    private fun setSavedDramasRV() {
        binding.rvSavedDramas.run {
            adapter = SavedDramasListAdapter(compositeDisposable, onItemClick = {
                startActivity(Intent(activity, DetailActivity::class.java).apply {
                    putExtra(EXTRA_VIDEO_ID, it.videoId)
                })
            }, onItemDelete = {
                viewModel.deleteBookmarkedDrama(it.dramaInfoSlug, it.episode.toString())
            })
            addItemDecoration(object : CustomItemDecoration() {
                override fun setSpacingForDirection(
                    outRect: Rect,
                    layoutManager: RecyclerView.LayoutManager?,
                    position: Int,
                    itemCount: Int
                ) {
                    val column = position % 2
                    val spacing =
                        resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)

                    outRect.left = column * spacing / 2
                    outRect.right = spacing - (column + 1) * spacing / 2
                    outRect.top = if (position == 0 || position == 1) {
                        resources.getDimensionPixelOffset(R.dimen.recyclerview_spacing_size_20dp)
                    } else {
                        0
                    }
                    outRect.bottom =
                        resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)
                }
            })
            layoutManager?.let {
                addOnScrollListener(object : EndlessRVScrollListener(it, 4) {
                    override fun onLoadMore(page: Int) {
                        if (viewModel.hasNextData.value != false) {
                            viewModel.getDramasBookmark(page)
                        }
                    }
                })
            }
        }
    }

    private fun observeViewModel() {
        /** 로딩 observe */
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.srlEvaluation.isRefreshing = it
        })

        /** 드라마 개수 observe */
        viewModel.totalCount.observe(viewLifecycleOwner, Observer {
            parentViewModel.setDramasTotalCount(it)
        })
    }

    companion object {
        fun newInstance(): Fragment = SavedDramasFragment()
    }
}