package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ItemHomeParentDramasBinding
import com.leisurely.people.enjoyd.ui.common.adapter.DramaListAdapter
import com.leisurely.people.enjoyd.util.CustomItemDecoration

/**
 * 홈화면 드라마 보여주는 리스트 형식 UI 에서 GridLayoutManager 로 보여주기 위한 부모 Wrapper 어댑터 클래스
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.13
 */
class HomeParentDramasRVAdapter(private val childAdapter: DramaListAdapter) :
    RecyclerView.Adapter<HomeParentDramasRVAdapter.HomeParentDramasVH>() {

    private val childDramaRecycledViewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentDramasVH {
        return HomeParentDramasVH(
            ItemHomeParentDramasBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            setChildDramaRV()
        }
    }

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: HomeParentDramasVH, position: Int) = Unit

    inner class HomeParentDramasVH(private val binding: ItemHomeParentDramasBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setChildDramaRV() {
            binding.rvParent.run {
                adapter = childAdapter
                isNestedScrollingEnabled = false
                setRecycledViewPool(childDramaRecycledViewPool)
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
                        outRect.bottom =
                            resources.getDimensionPixelSize(R.dimen.recyclerview_spacing_size_16dp)
                    }
                })
            }
        }

    }
}