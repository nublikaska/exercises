package com.nublikaska.exercises.ui_kit.helper

import android.text.Editable
import android.text.TextWatcher

open class SimpleTextWatcher : TextWatcher {

    override fun afterTextChanged(editable: Editable) {}
    override fun beforeTextChanged(value: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(value: CharSequence, start: Int, before: Int, count: Int) {}
}