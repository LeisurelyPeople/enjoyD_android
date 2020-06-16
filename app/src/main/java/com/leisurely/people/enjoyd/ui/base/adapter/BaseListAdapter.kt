package com.leisurely.people.enjoyd.ui.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

/**
 * ListAdapter Base 클래스
 * [onItemClick]을 넘겨주면 자동으로 item click listener 설정
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

abstract class BaseListAdapter<T>(
    diffCallback: DiffUtil.ItemCallback<T>,
    private val onItemClick: OnRecyclerViewItemClick<T>? = null
) : ListAdapter<T, BaseItemVH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemVH {
        return BaseItemVH(onCreateBinding(parent, viewType)).also {
            if (onItemClick != null) { // onItemClick 변수가 null 이 아닌 경우 자동으로 아이템 클릭 리스너 설정
                it.binding.root.setOnClickListener { _ ->
                    onItemClick.invoke(currentList[it.bindingAdapterPosition])
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
