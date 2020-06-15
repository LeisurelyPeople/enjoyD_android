package com.leisurely.people.enjoyd.ui.base.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

typealias OnRecyclerViewItemClick<T> = ((T) -> Unit)

abstract class BaseRVAdapter<T>(
    private val onItemClick: OnRecyclerViewItemClick<T>? = null
) : RecyclerView.Adapter<BaseItemVH>() {

    protected val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemVH {
        return BaseItemVH(onCreateBinding(parent, viewType)).also {
            if (onItemClick != null) {
                it.binding.root.setOnClickListener { _ ->
                    onItemClick.invoke(items[it.adapterPosition])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: BaseItemVH, position: Int) =
        onBindView(holder.binding, holder, items[position])

    protected abstract fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: T
    )

    protected abstract fun onCreateBinding(
        parent: ViewGroup,
        viewType: Int
    ): ViewDataBinding


    override fun getItemCount(): Int = items.size

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun replaceAll(replaceItems: List<T>?) {
        this.items.run {
            clear()
            replaceItems?.let {
                addAll(it)
            }
        }
        notifyDataSetChanged()
    }

    fun add(item: T?) {
        this.items.run {
            item?.let {
                add(it)
                notifyItemInserted(items.size - 1)
            }
        }
    }

    fun addToBeginning(item: T?) {
        this.items.run {
            item?.let {
                add(0, it)
                notifyItemInserted(0)
            }
        }
    }

    fun addAll(addItems: List<T>?) {
        this.items.run {
            addItems?.let {
                addAll(it)
                notifyItemRangeChanged(items.size - it.size, it.size)
            }
        }
    }

    fun remove(item: T?) {
        val position = this.items.indexOf(item)
        if (position > -1) {
            this.items.remove(item)
            notifyItemRemoved(position)
        }
    }

    fun removeRange(fromIdx: Int, toIdx: Int) {
        this.items.subList(fromIdx, toIdx).clear()
        notifyItemRangeChanged(fromIdx, toIdx - fromIdx)
    }
}
