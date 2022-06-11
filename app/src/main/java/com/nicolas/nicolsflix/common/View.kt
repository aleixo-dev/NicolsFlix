package com.nicolas.nicolsflix.common

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun Fragment.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    context?.let { context ->
        Toast.makeText(context, message, duration).show()
    }

