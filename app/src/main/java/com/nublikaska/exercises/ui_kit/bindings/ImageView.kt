package com.nublikaska.exercises.ui_kit.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

internal val picasso by lazy { Picasso.get()}

@BindingAdapter("srcCompat")
fun ImageView.setImageResource(drawableResId: Int?) {

    when (drawableResId) {

        null -> setImageDrawable(null)
        else -> setImageResource(drawableResId)
    }
}

@BindingAdapter("imageUrl")
fun ImageView.setImageResource(imageUrl: String?) {

    imageUrl ?: return
    picasso.load(imageUrl).into(this)
}