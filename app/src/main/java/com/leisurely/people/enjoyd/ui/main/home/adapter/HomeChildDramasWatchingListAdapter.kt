package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasWatchingResponse
import com.leisurely.people.enjoyd.databinding.ItemDramasWatchingBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import com.leisurely.people.enjoyd.ui.base.adapter.OnRecyclerViewItemClick

/**
 * 시청중인 드라마리스트 (Child List Adapter)
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class HomeChildDramasWatchingListAdapter(
    onItemClick: OnRecyclerViewItemClick<DramasWatchingResponse>
) : BaseListAdapter<DramasWatchingResponse>(object :
    DiffUtil.ItemCallback<DramasWatchingResponse>() {
    override fun areItemsTheSame(
        oldItem: DramasWatchingResponse,
        newItem: DramasWatchingResponse
    ): Boolean = oldItem.slug == newItem.slug

    override fun areContentsTheSame(
        oldItem: DramasWatchingResponse,
        newItem: DramasWatchingResponse
    ): Boolean = oldItem == newItem

}, onItemClick) {

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemDramasWatchingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                root.layoutParams.width =
                    (Resources.getSystem().displayMetrics.widthPixels * 0.405).toInt()
            }
    }

    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasWatchingResponse
    ) {
        (binding as ItemDramasWatchingBinding).item = item
    }
}