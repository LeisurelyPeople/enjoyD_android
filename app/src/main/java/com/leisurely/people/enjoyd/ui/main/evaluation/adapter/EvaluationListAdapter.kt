package com.leisurely.people.enjoyd.ui.main.evaluation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leisurely.people.enjoyd.data.remote.data.response.evaluation.DramaEvaluationResponse
import com.leisurely.people.enjoyd.databinding.ItemDramaEvaluationBinding

/**
 * 평가하기 탭에 있는 드라마 recyclerview
 *
 * @author Wayne
 * @since v1.0.0 / 2020.12.14
 */
class EvaluationListAdapter(private val ratingBarChanged: (rating: Float, idx: Int) -> Unit) :
    ListAdapter<DramaEvaluationResponse, EvaluationListAdapter.EvaluationVH>(object :
        DiffUtil.ItemCallback<DramaEvaluationResponse>() {
        override fun areItemsTheSame(
            oldItem: DramaEvaluationResponse,
            newItem: DramaEvaluationResponse
        ): Boolean = oldItem.pk == newItem.pk

        override fun areContentsTheSame(
            oldItem: DramaEvaluationResponse,
            newItem: DramaEvaluationResponse
        ): Boolean = oldItem == newItem

    }) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EvaluationVH {
        return EvaluationVH(
            ItemDramaEvaluationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            binding.rbsDramaGrade.setOnRatingBarChangeListener { _, rating, _ ->
                if (rating == 0f) return@setOnRatingBarChangeListener
                binding.item?.let { ratingBarChanged(rating, it.pk) }
            }
        }
    }

    override fun onBindViewHolder(holder: EvaluationVH, position: Int) {
        holder.binding.rbsDramaGrade.rating = 0f
        holder.binding.item = getItem(position)
    }

    inner class EvaluationVH(val binding: ItemDramaEvaluationBinding) :
        RecyclerView.ViewHolder(binding.root)
}