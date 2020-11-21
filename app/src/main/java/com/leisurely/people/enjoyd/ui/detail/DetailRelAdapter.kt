package com.leisurely.people.enjoyd.ui.detail

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSlugRelatedSearchResponseItem
import com.leisurely.people.enjoyd.databinding.ItemRelBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter
import com.leisurely.people.enjoyd.util.ext.setOnSingleClickListener

/**
 * 연관 드라마 adapter
 *
 * @author ricky
 * @since v1.0.0 / 2020.10.18
 */
class DetailRelAdapter : BaseRVAdapter<DramasSlugRelatedSearchResponseItem>() {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSlugRelatedSearchResponseItem
    ) {
        (binding as ItemRelBinding).item = item
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemRelBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            this.root.setOnSingleClickListener {
                val intent = Intent(parent.context, DetailActivity::class.java).apply {
                    // TODO 이 값은 바뀌어야 함
                    putExtra("dramaSlug", item.title ?: "")
                }
                parent.context.startActivity(intent)
            }
        }
    }

}