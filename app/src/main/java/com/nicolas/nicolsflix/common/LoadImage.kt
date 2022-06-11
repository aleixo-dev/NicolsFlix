package com.nicolas.nicolsflix.common

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
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

fun ImageView.loadImage(context: Context,url: String) {

    val circularProgressDrawable = CircularProgressDrawable(context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()

    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(circularProgressDrawable)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(this)
}