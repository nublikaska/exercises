package com.nublikaska.exercises.ui_kit.extensions

import android.widget.TextView
import com.nublikaska.exercises.ui_kit.helper.SimpleTextWatcher

inline fun TextView.onTextChanged(crossinline action: (text: CharSequence?) -> Unit) {

    addTextChangedListener(object : SimpleTextWatcher() {

        override fun onTextChanged(value: CharSequence, start: Int, before: Int, count: Int) {

            action.invoke(value)
        }
    })
}