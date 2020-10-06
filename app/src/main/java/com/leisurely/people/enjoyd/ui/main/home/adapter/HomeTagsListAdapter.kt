package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.data.remote.data.PagingResponse
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.databinding.ItemHomeTagsBinding
import com.leisurely.people.enjoyd.model.ResultWrapperModel
import com.leisurely.people.enjoyd.ui.common.adapter.DramaTagsRVAdapter
import com.leisurely.people.enjoyd.util.CustomItemDecoration

/**
 * 홈 화면 태그 리스트 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class HomeTagsListAdapter(private val onItemClickedChanged: (DramasTagsResponse) -> Unit) :
    ListAdapter<ResultWrapperModel<List<DramasTagsResponse>>, HomeTagsListAdapter.HomeTagsVH>(
        object : DiffUtil.ItemCallback<ResultWrapperModel<List<DramasTagsResponse>>>() {
            override fun areItemsTheSame(
                oldItem: ResultWrapperModel<List<DramasTagsResponse>>,
                newItem: ResultWrapperModel<List<DramasTagsResponse>>
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ResultWrapperModel<List<DramasTagsResponse>>,
                newItem: ResultWrapperModel<List<DramasTagsResponse>>
            ): Boolean = oldItem == newItem
        }) {

    private val dramaTagsRecycledViewPool = RecyclerView.RecycledViewPool()

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

    override fun onBindViewHolder(holder: HomeTagsVH, position: Int) {
        holder.bind()
    }

    inner class HomeTagsVH(private val binding: ItemHomeTagsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val dramaTagsRVAdapter = DramaTagsRVAdapter {
            onItemClickedChanged(it)
        }

        fun setDramaTagChildRV() {
            binding.rvDramaTag.run {
                adapter = dramaTagsRVAdapter
                setRecycledViewPool(dramaTagsRecycledViewPool)
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
            dramaTagsRVAdapter.setDramaTags(currentList.singleOrNull()?.data ?: mutableListOf())
        }
    }
}