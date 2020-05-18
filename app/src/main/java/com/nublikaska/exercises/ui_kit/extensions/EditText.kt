package com.nublikaska.exercises.ui_kit.extensions

import android.widget.EditText

internal inline val EditText.canSetSelectionEnd: Boolean
    get() = this.text?.isNotEmpty() == true && this.selectionStart <= 0

internal fun EditText.trySetSelectionEnd() {

    if (canSetSelectionEnd) setSelection(text?.length ?: 0)
}