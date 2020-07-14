package com.leisurely.people.enjoyd.ui.search.basic

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import com.leisurely.people.enjoyd.databinding.ItemBasicBinding
import com.leisurely.people.enjoyd.ui.base.adapter.BaseItemVH
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import kotlinx.android.synthetic.main.item_basic.view.*

/**
 * 평가 화면에서 사용되는 List 의 어뎁터
 *
 * @author ricky
 * @since v1.0.0 / 2020.07.01
 */
class BasicListAdapter : BaseListAdapter<String>(object : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem // check uniqueness
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem // check contents
    }
}) {
    override fun onBindView(binding: ViewDataBinding, viewHolder: BaseItemVH, item: String) {
        binding.setVariable(BR.basic, item)
    }

    override fun onCreateBinding(parent: ViewGroup, viewType: Int): ViewDataBinding {
        return ItemBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply {
                root.basic_title.setOnClickListener {
                    Toast.makeText(it.context, root.basic_title.text, Toast.LENGTH_SHORT).show()
                }
            }
    }
}
