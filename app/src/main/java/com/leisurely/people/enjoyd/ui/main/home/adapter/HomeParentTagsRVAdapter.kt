package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ItemHomeParentTagsBinding
import com.leisurely.people.enjoyd.ui.common.adapter.DramaTagsListAdapter
import com.leisurely.people.enjoyd.util.CustomItemDecoration

/**
 * 홈화면 태그들을 보여주는 리스트 형식 UI 에서 GridLayoutManager 로 보여주기 위한 부모 Wrapper 어댑터 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.13
 */
class HomeParentTagsRVAdapter(private val childAdapter: DramaTagsListAdapter) :
    RecyclerView.Adapter<HomeParentTagsRVAdapter.HomeParentTagsVH>() {

    private val childTagsRecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentTagsVH {
        return HomeParentTagsVH(
            ItemHomeParentTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            setChildTagsRV()
        }
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HomeParentTagsVH, position: Int) = Unit

    inner class HomeParentTagsVH(private val binding: ItemHomeParentTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun setChildTagsRV() {
            binding.rvParent.run {
                adapter = childAdapter
                setRecycledViewPool(childTagsRecycledViewPool)
                itemAnimator = null
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
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_8dp)
                        }
                    }
                })
            }
        }
    }
}