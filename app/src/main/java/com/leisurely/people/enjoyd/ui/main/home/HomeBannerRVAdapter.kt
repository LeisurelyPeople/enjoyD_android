package com.leisurely.people.enjoyd.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.data.remote.data.response.home.DramasBannerResponse
import com.leisurely.people.enjoyd.databinding.ItemHomeBannerBinding

/**
 * 홈화면 배너 어댑터
 *
 * @author Wayne
 * @since v1.0.0 / 2020.09.28
 */
class HomeBannerRVAdapter : RecyclerView.Adapter<HomeBannerRVAdapter.HomeBannerVH>() {

    private var item: DramasBannerResponse? = null

    fun setHomeBannerData(data: DramasBannerResponse?) {
        data?.let {
            item = it
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeBannerVH {
        return HomeBannerVH(
            ItemHomeBannerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = if (item == null) 0 else 1

    override fun onBindViewHolder(holder: HomeBannerVH, position: Int) {
        holder.bind()
    }

    inner class HomeBannerVH(private val binding: ItemHomeBannerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            binding.item = item
        }
    }
}