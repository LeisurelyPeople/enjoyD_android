package com.leisurely.people.enjoyd.ui.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.data.remote.data.response.DramasItemResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter
import com.leisurely.people.enjoyd.ui.base.adapter.OnRecyclerViewItemClick

/**
 * 드라마 아이템을 보여주는 RecyclerView Adapter
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.29
 */
class DramaListAdapter(onItemClick: OnRecyclerViewItemClick<DramasItemResponse>) :
    BaseListAdapter<DramasItemResponse>(object : DiffUtil.ItemCallback<DramasItemResponse>() {
        override fun areItemsTheSame(
            oldItem: DramasItemResponse,
            newItem: DramasItemResponse
        ): Boolean = oldItem.slug == newItem.slug

        override fun areContentsTheSame(
            oldItem: DramasItemResponse,
            newItem: DramasItemResponse
        ): Boolean = oldItem == newItem
    }, onItemClick) {

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemDramaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasItemResponse
    ) {
        (binding as ItemDramaBinding).item = item
    }
}