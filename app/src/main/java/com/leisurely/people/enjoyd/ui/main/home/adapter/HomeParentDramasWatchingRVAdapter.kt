package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ItemHomeDramasWatchingBinding
import com.leisurely.people.enjoyd.util.CustomItemDecoration

/**
 * 홈화면에서 시청중인 드라마 리스트 UI를 Horizontal(LinearLayoutManager) 로 보여주기 위한
 * 부모 Wrapper 어댑터 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class HomeParentDramasWatchingRVAdapter(
    private val childAdapter: HomeChildDramasWatchingListAdapter
) : RecyclerView.Adapter<HomeParentDramasWatchingRVAdapter.HomeParentDramasWatchingVH>() {

    private val childDramasWatchingRecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentDramasWatchingVH {
        return HomeParentDramasWatchingVH(
            ItemHomeDramasWatchingBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        ).apply {
            setChildDramasWatchingRV()
        }
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HomeParentDramasWatchingVH, position: Int) = Unit

    inner class HomeParentDramasWatchingVH(private val binding: ItemHomeDramasWatchingBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setChildDramasWatchingRV() {
            binding.rvParent.run {
                adapter = childAdapter
                setRecycledViewPool(childDramasWatchingRecycledViewPool)
                addItemDecoration(object : CustomItemDecoration() {
                    override fun setSpacingForDirection(
                        outRect: Rect,
                        layoutManager: RecyclerView.LayoutManager?,
                        position: Int,
                        itemCount: Int
                    ) {
                        outRect.left = if (position == 0) {
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                        } else {
                            0
                        }
                        outRect.right =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_12dp)
                    }

                })
            }
        }
    }
}