package com.leisurely.people.enjoyd.ui.main.mypage.dibs.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding3.view.clicks
import com.leisurely.people.enjoyd.data.remote.data.response.mypage.DramasBookmarkResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaSavedBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

/**
 * 찜한 드라마 RecyclerView Adapter class
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.31
 */
class SavedDramasListAdapter(
    private val compositeDisposable: CompositeDisposable,
    private val onItemDelete: (DramasBookmarkResponse) -> Unit,
    private val onItemClick: (DramasBookmarkResponse) -> Unit
) : ListAdapter<DramasBookmarkResponse, SavedDramasListAdapter.SavedDramasVH>(object :
    DiffUtil.ItemCallback<DramasBookmarkResponse>() {
    override fun areItemsTheSame(
        oldItem: DramasBookmarkResponse,
        newItem: DramasBookmarkResponse
    ): Boolean = oldItem.dramaInfoSlug == newItem.dramaInfoSlug

    override fun areContentsTheSame(
        oldItem: DramasBookmarkResponse,
        newItem: DramasBookmarkResponse
    ): Boolean = oldItem == newItem
}) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedDramasVH {
        return SavedDramasVH(
            ItemDramaSavedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            with(binding) {
                root.setOnClickListener { item?.let(onItemClick) }
                cbBookmark.clicks()
                    .debounce(200, TimeUnit.MILLISECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe {
                        if (cbBookmark.isChecked.not()) {
                            item?.let(onItemDelete)
                        }
                    }.addTo(compositeDisposable)
            }
        }
    }

    override fun onBindViewHolder(holder: SavedDramasVH, position: Int) {
        holder.bind()
    }

    inner class SavedDramasVH(val binding: ItemDramaSavedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.item = getItem(bindingAdapterPosition)
            binding.cbBookmark.isChecked = true // 이미 북마크된 드라마 정보 이기 때문에 항상 true 값으로 유지
        }
    }
}