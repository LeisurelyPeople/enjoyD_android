package com.leisurely.people.enjoyd.ui.main.evaluation

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.FragmentEvaluationBinding
import com.leisurely.people.enjoyd.ui.base.BaseFragment
import com.leisurely.people.enjoyd.ui.main.evaluation.adapter.EvaluationHeaderTextRVAdapter
import com.leisurely.people.enjoyd.ui.main.evaluation.adapter.EvaluationListAdapter
import com.leisurely.people.enjoyd.ui.main.home.adapter.HomeDramasWatchingTitleListAdapter
import com.leisurely.people.enjoyd.util.CustomItemDecoration
import com.leisurely.people.enjoyd.util.EndlessRVScrollListener
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 평가하기 탭 Fragment 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.26
 */
class EvaluationFragment :
    BaseFragment<FragmentEvaluationBinding, EvaluationViewModel>(R.layout.fragment_evaluation),
    SwipeRefreshLayout.OnRefreshListener {

    override val viewModel: EvaluationViewModel by viewModel()

    /** 평가하기 화면 타이틀 Text UI를 보여주기 위한 RecyclerView Adapter */
    private val evaluationHeaderTextRVAdapter by lazy {
        EvaluationHeaderTextRVAdapter()
    }

    /** 평가하기 화면 드라마 리스트 UI를 보여주기 위한 List Adapter */
    private val evaluationListAdapter by lazy {
        EvaluationListAdapter { rating: Float, pk: Int ->
            viewModel.onDramaRatingChanged(rating, pk)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSwipeRefreshLayout()
        setEvaluationRV()
        observeViewModelLiveData()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDramasList(1)
    }

    override fun onRefresh() {
        viewModel.getDramasList(1)
    }

    private fun setSwipeRefreshLayout() {
        binding.srlEvaluation.setOnRefreshListener(this)
    }

    private fun setEvaluationRV() {
        binding.rvEvaluationDrama.run {
            adapter = ConcatAdapter(evaluationHeaderTextRVAdapter, evaluationListAdapter)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    /** 시청중인 드라마 리스트 타이틀 spacing 값 설정 */
                    if (parent.getChildViewHolder(view) is EvaluationListAdapter.EvaluationVH) {
                        outRect.bottom =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_24dp)
                        outRect.left =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        outRect.right =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                    }
                }
            })
            layoutManager?.let {
                addOnScrollListener(object : EndlessRVScrollListener(it, 4) {
                    override fun onLoadMore(page: Int) {
                        if (viewModel.hasNextData.value != false) {
                            viewModel.getDramasList(page)
                        }
                    }
                })
            }
        }
    }

    private fun observeViewModelLiveData() {
        /** 로딩 observe */
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.srlEvaluation.isRefreshing = it
        })

        /** 드라마(팡가하기 위한) 데이터 observer */
        viewModel.evaluationDramasLiveData.observe(viewLifecycleOwner, Observer {
            evaluationListAdapter.submitList(it)
        })
    }

    companion object {
        fun newInstance(): EvaluationFragment = EvaluationFragment()
    }
}