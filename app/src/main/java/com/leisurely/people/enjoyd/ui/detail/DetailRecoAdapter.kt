package com.leisurely.people.enjoyd.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponseItem
import com.leisurely.people.enjoyd.databinding.ItemSearchResultBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter

/**
 * 연관 드라마 adapter
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class DetailRecoAdapter : BaseRVAdapter<DramasSearchResponseItem>() {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSearchResponseItem
    ) {
        (binding as ItemSearchResultBinding).searchResult = item
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {

        }
    }

}