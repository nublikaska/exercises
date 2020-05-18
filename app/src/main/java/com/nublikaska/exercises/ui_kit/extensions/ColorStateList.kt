package com.nublikaska.exercises.ui_kit.extensions

import android.content.res.ColorStateList
import androidx.annotation.ColorInt

internal fun generatePressedColorStateList(@ColorInt pressedColor: Int,
                                           @ColorInt notPressedColor: Int) = ColorStateList(

    arrayOf(
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf(-android.R.attr.state_pressed)
    ),

    intArrayOf(
        pressedColor,
        notPressedColor
    )
)

internal fun generateEnabledColorStateList(@ColorInt enabledColor: Int,
                                           @ColorInt notEnabledColor: Int) = ColorStateList(

    arrayOf(
        intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled)
    ),

    intArrayOf(
        enabledColor,
        notEnabledColor
    )
)

internal fun generateFocusedColorStateList(@ColorInt focusedColor: Int,
                                           @ColorInt notFocusedColorColor: Int) = ColorStateList(

    arrayOf(
        intArrayOf(android.R.attr.state_focused),
        intArrayOf(-android.R.attr.state_focused)
    ),

    intArrayOf(
        focusedColor,
        notFocusedColorColor
    )
)

internal fun generateCheckedColorStateList(@ColorInt checkedColor: Int,
                                           @ColorInt notCheckedColor: Int) = ColorStateList(

    arrayOf(
        intArrayOf(android.R.attr.state_checked),
        intArrayOf(-android.R.attr.state_checked)
    ),

    intArrayOf(
        checkedColor,
        notCheckedColor
    )
)