package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.databinding.ItemHomeBannerBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter

/**
 * 홈화면 배너 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class HomeBannerListAdapter :
    BaseListAdapter<DramasBannerResponse>(object : DiffUtil.ItemCallback<DramasBannerResponse>() {
        override fun areItemsTheSame(
            oldItem: DramasBannerResponse,
            newItem: DramasBannerResponse
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: DramasBannerResponse,
            newItem: DramasBannerResponse
        ): Boolean = oldItem.slug == newItem.slug

    }) {
    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemHomeBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasBannerResponse
    ) {
        (binding as ItemHomeBannerBinding).item = item
    }
}