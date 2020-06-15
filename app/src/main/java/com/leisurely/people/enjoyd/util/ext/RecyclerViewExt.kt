package com.leisurely.people.enjoyd.util.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter

// RecyclerView Adapter 사용 시
@Suppress("UNCHECKED_CAST")
@BindingAdapter("addAll")
fun RecyclerView.addAllItems(items: List<Any>?) {
    (adapter as? BaseRVAdapter<Any>)?.addAll(items)
}


// RecyclerView Adapter 사용 시
@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAllItems(items: List<Any>?) {
    (adapter as? BaseRVAdapter<Any>)?.replaceAll(items)
}

// ListAdapter 사용 시
@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun RecyclerView.submitList(items: List<Any>?) {
    (adapter as? BaseListAdapter<Any>)?.submitList(items)
}