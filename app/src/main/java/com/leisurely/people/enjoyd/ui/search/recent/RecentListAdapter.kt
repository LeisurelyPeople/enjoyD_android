package com.leisurely.people.enjoyd.ui.search.recent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.data.local.RecentSearch
import com.leisurely.people.enjoyd.databinding.ItemRecentBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.item_recent.view.*

/**
 * 평가 화면에서 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
class RecentListAdapter :
    BaseListAdapter<RecentSearch>(object : DiffUtil.ItemCallback<RecentSearch>() {
        override fun areItemsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
            return oldItem == newItem // check uniqueness
        }

        override fun areContentsTheSame(oldItem: RecentSearch, newItem: RecentSearch): Boolean {
            return oldItem == newItem // check contents
        }
    }) {
    override fun onBindView(binding: ViewDataBinding, viewHolder: BaseItemVH, item: RecentSearch) {
        binding.setVariable(BR.recent, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemRecentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                root.recent_delete.setOnClickListener {
                    val message = "${root.recent_title.text} 삭제"
                    Toast.makeText(parent.context, message, Toast.LENGTH_SHORT).show()
                }

                root.recent_title.setOnClickListener {
                    val message = root.recent_title.text
                    Toast.makeText(parent.context, message, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
