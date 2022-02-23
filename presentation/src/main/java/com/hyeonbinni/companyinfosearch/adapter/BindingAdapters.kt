package com.hyeonbinni.companyinfosearch

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, imageUrl: String) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("setBackgroundColorByHex")
fun setBackgroundColorByHex(view: View, color: String) {
    try {
        view.setBackgroundColor(Color.parseColor(color))
    } catch (e: Exception) {
        view.setBackgroundColor(Color.parseColor("#ffffff"))
    }
}