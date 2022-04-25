package com.nicolas.nicolsflix.upcoming.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object LoadImage {
    fun load(context: Context, url: String, view: ImageView) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(view)
    }
}