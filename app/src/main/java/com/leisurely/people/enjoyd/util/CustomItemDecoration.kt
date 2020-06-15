package com.leisurely.people.enjoyd.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class CustomItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position: Int = parent.getChildViewHolder(view).adapterPosition
        val itemCount: Int = state.itemCount
        setSpacingForDirection(outRect, parent.layoutManager, position, itemCount)
    }

    abstract fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager?,
        position: Int,
        itemCount: Int
    )
}