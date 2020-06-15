package com.leisurely.people.enjoyd.util.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.leisurely.people.enjoyd.R

@BindingAdapter("imgUrl")
fun ImageView.loadImageUrl(url: String?) {
    url?.let {
        Glide.with(this)
            .load(it)
            .transition(GenericTransitionOptions.with(R.anim.fragment_fade_enter))
            .into(this)
    }
}

@BindingAdapter("imgRes")
fun ImageView.setImageRes(resId: Int) {
    setImageResource(resId)
}