package com.leisurely.people.enjoyd.ui.main.home.adapter

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.buildSpannedString
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.R
import com.leisurely.people.enjoyd.databinding.ItemHomeDramasTitleBinding
import com.leisurely.people.enjoyd.util.CustomTypefaceSpan


/**
 * 홈화면 드라마 리스트 타이틀(텍스트) 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.10.19
 */
class HomeDramasTitleListAdapter :
    ListAdapter<String, HomeDramasTitleListAdapter.HomeDramasTitleVH>(object :
        DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDramasTitleVH {
        return HomeDramasTitleVH(
            ItemHomeDramasTitleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeDramasTitleVH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeDramasTitleVH(private val binding: ItemHomeDramasTitleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: String) {
            binding.tvDramasTitle.text = getDramasTitleStringBuilder(binding.root.context, tag)
        }

        private fun getDramasTitleStringBuilder(context: Context, tag: String): SpannedString {
            val typefaceNotoSansBold =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_bold)
            val typefaceNotoSansMedium =
                ResourcesCompat.getFont(context, R.font.noto_sans_kr_medium)

            return buildSpannedString {
                append(SpannableString(tag).apply {
                    setSpan(
                        CustomTypefaceSpan(typefaceNotoSansBold),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(
                            context.resources.getColor(
                                R.color.color_main_100,
                                context.theme
                            )
                        ),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
                append(SpannableString(" 웹드라마").apply {
                    setSpan(
                        CustomTypefaceSpan(typefaceNotoSansMedium),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    setSpan(
                        ForegroundColorSpan(
                            context.resources.getColor(
                                R.color.color_grey_0,
                                context.theme
                            )
                        ),
                        0,
                        this.length,
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                })
            }
        }

    }
}