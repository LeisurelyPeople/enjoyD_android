package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.databinding.ItemHomeDramasViewAllBinding
import com.leisurely.people.enjoyd.model.ResultWrapperModel

/**
 * 홈화면 드라마 더보기 버튼 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.06
 */
class HomeDramasViewAllListAdapter(private val onItemClick: () -> Unit) :
    ListAdapter<ResultWrapperModel<Unit>, HomeDramasViewAllListAdapter.HomeDramasViewMoreVH>(
        object : DiffUtil.ItemCallback<ResultWrapperModel<Unit>>() {
            override fun areItemsTheSame(
                oldItem: ResultWrapperModel<Unit>,
                newItem: ResultWrapperModel<Unit>
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: ResultWrapperModel<Unit>,
                newItem: ResultWrapperModel<Unit>
            ): Boolean = oldItem == newItem
        }) {

    var tag: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDramasViewMoreVH {
        return HomeDramasViewMoreVH(
            ItemHomeDramasViewAllBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                onItemClick()
            }
        }
    }

    override fun onBindViewHolder(holder: HomeDramasViewMoreVH, position: Int) = Unit

    inner class HomeDramasViewMoreVH(binding: ItemHomeDramasViewAllBinding) :
        RecyclerView.ViewHolder(binding.root)
}