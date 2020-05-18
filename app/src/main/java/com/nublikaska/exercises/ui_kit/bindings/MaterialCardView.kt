package com.nublikaska.exercises.ui_kit.bindings

import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView

@BindingAdapter("checked")
fun MaterialCardView.setIsChecked(checked: Boolean) {

    when (isChecked == checked) {

        true -> Unit
        else -> toggle()
    }
}