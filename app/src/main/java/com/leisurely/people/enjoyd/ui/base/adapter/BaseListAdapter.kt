package com.leisurely.people.enjoyd.ui.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val onItemClick: OnRecyclerViewItemClick<T>? = null
) : ListAdapter<T, BaseItemVH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemVH {
        return BaseItemVH(onCreateBinding(parent, viewType)).also {
            if (onItemClick != null) {
                it.binding.root.setOnClickListener { _ ->
                    onItemClick.invoke(currentList[it.adapterPosition])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseItemVH, position: Int) =
        onBindView(holder.binding, holder, getItem(position))

    protected abstract fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: T
    )

    protected abstract fun onCreateBinding(
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding
}
