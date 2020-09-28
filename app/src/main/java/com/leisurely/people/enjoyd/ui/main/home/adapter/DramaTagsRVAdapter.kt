package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasTagsResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaTagBinding

/**
 * 드라마 태그 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class DramaTagsRVAdapter(private val onItemClickedChanged: (DramasTagsResponse) -> Unit) :
    RecyclerView.Adapter<DramaTagsRVAdapter.DramaTagsVH>() {

    private val items: MutableList<DramasTagsResponse> = mutableListOf()

    private var checkedPosition = 0

    fun setDramaTags(items: List<DramasTagsResponse>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DramaTagsVH {
        return DramaTagsVH(
            ItemDramaTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                checkedPosition = bindingAdapterPosition
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DramaTagsVH, position: Int) {
        holder.bind(items[position])
    }

    inner class DramaTagsVH(private val binding: ItemDramaTagBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: DramasTagsResponse) {
            binding.item = item
            binding.isChecked = bindingAdapterPosition == checkedPosition
            if (binding.isChecked) {
                onItemClickedChanged(items[bindingAdapterPosition])
            }
        }
    }
}