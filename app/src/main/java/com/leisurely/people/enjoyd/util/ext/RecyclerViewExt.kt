package com.leisurely.people.enjoyd.util.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.ui.base.adapter.BaseListAdapter
import com.leisurely.people.enjoyd.ui.base.adapter.BaseRVAdapter

/**
 * RecyclerView Extension 관리 파일
 *
 * @author Wayne
 * @since v1.0.0 / 2020.06.15
 */

/** RecyclerView Adapter 사용 시(아이템 추가) */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("addAll")
fun RecyclerView.addAllItems(items: List<Any>?) {
    (adapter as? BaseRVAdapter<Any>)?.addAll(items)
}


/** RecyclerView Adapter 사용 시 사용(아이템 대체) */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("replaceAll")
fun RecyclerView.replaceAllItems(items: List<Any>?) {
    (adapter as? BaseRVAdapter<Any>)?.replaceAll(items)
}

/** ListAdapter 사용 시 사용(아이템 비교 및 대체) */
@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun RecyclerView.submitList(items: List<Any>?) {
    (adapter as? ListAdapter<Any, *>)?.submitList(items)
}