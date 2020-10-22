package com.leisurely.people.enjoyd.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaTagBinding
import com.leisurely.people.enjoyd.model.DramasTagsModel
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter

/**
 * 드라마 태그 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramaTagsListAdapter(private val onItemClickedChanged: (DramasTagsModel) -> Unit) :
    ListAdapter<DramasTagsModel, DramaTagsListAdapter.DramaTagsVH>(object :
        DiffUtil.ItemCallback<DramasTagsModel>() {
        override fun areItemsTheSame(
            oldItem: DramasTagsModel,
            newItem: DramasTagsModel
        ): Boolean = (oldItem.name == newItem.name || oldItem.isSelected == newItem.isSelected)

        override fun areContentsTheSame(
            oldItem: DramasTagsModel,
            newItem: DramasTagsModel
        ): Boolean = oldItem == newItem

    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaTagsVH {
        return DramaTagsVH(
            ItemDramaTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onItemClickedChanged(getItem(bindingAdapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: DramaTagsVH, position: Int) {
        holder.bind()
    }

    inner class DramaTagsVH(private val binding: ItemDramaTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.item = getItem(bindingAdapterPosition)
        }
    }
}