package com.leisurely.people.enjoyd.ui.search.searchResult

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.data.remote.data.response.DramasSearchResponseItem
import com.leisurely.people.enjoyd.databinding.ItemSearchResultBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter

/**
 * 검색 결과를 보여줄 때 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
class SearchResultAdapter : BaseListAdapter<DramasSearchResponseItem>(
    object : DiffUtil.ItemCallback<DramasSearchResponseItem>() {
        override fun areItemsTheSame(
            oldItem: DramasSearchResponseItem,
            newItem: DramasSearchResponseItem
        ): Boolean {
            return oldItem.id == newItem.id // check uniqueness
        }

        override fun areContentsTheSame(
            oldItem: DramasSearchResponseItem,
            newItem: DramasSearchResponseItem
        ): Boolean {
            return oldItem == newItem // check contents
        }
    }
) {
    override fun onBindView(
        binding: ViewDataBinding,
        viewHolder: BaseItemVH,
        item: DramasSearchResponseItem
    ) {
        binding.setVariable(BR.searchResult, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemSearchResultBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            root.setOnClickListener {
                Toast.makeText(it.context, searchResult?.title, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
