package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.databinding.ItemHomeTagsBinding
import com.leisurely.people.enjoyd.ui.base.adapter.OnRecyclerViewItemClick
import com.leisurely.people.enjoyd.util.CustomItemDecoration

/**
 * 홈 화면 태그 리스트 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class HomeTagsRVAdapter(private val onItemClick: OnRecyclerViewItemClick<DramasTagsResponse>) :
    RecyclerView.Adapter<HomeTagsRVAdapter.HomeTagsVH>() {

    private val dramaTagsRecycledViewPool = RecyclerView.RecycledViewPool()

    private val items: MutableList<DramasTagsResponse> = mutableListOf()

    fun setDramaTagItems(items: List<DramasTagsResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeTagsVH {
        return HomeTagsVH(
            ItemHomeTagsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            setDramaTagChildRV()
        }
    }

    override fun getItemCount(): Int = if (items.isNullOrEmpty()) 0 else 1

    override fun onBindViewHolder(holder: HomeTagsVH, position: Int) {
        holder.bind()
    }

    inner class HomeTagsVH(private val binding: ItemHomeTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setDramaTagChildRV() {
            binding.rvDramaTag.run {
                adapter = DramaTagsRVAdapter {
                    onItemClick(it)
                }
                setRecycledViewPool(dramaTagsRecycledViewPool)
                setHasFixedSize(false)
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

        fun bind() {
            (binding.rvDramaTag.adapter as DramaTagsRVAdapter).setDramaTags(items)
        }

    }

}