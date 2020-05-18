package com.nublikaska.exercises.ui_kit.extensions

import android.content.Context
import android.media.AudioManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.content.getSystemService

inline val Context.inputMethodManager: InputMethodManager?
    get() = getSystemService<InputMethodManager>()

inline val Context.audioManager: AudioManager?
    get() = getSystemService<AudioManager>()

fun Context.getColorCompat(@ColorRes colorResId: Int) = ContextCompat.getColor(this, colorResId)