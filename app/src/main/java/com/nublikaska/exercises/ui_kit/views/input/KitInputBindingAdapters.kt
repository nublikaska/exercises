package com.nublikaska.exercises.ui_kit.views.input

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.nublikaska.exercises.ui_kit.extensions.onTextChanged

@BindingAdapter("KitInputTextChanged", requireAll = false)
fun KitInput.bindEvent(listener: InverseBindingListener) = editText?.onTextChanged {

    listener.onChange()
}

@InverseBindingAdapter(attribute = "text", event = "KitInputTextChanged")
fun KitInput.getText(): String = text

@BindingAdapter("text")
fun KitInput.setText(newText: String) {

    if (text != newText) text = newText
}



