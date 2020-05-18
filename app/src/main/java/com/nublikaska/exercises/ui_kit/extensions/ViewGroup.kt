package com.nublikaska.exercises.ui_kit.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.transition.Transition
import androidx.transition.TransitionManager

private val ViewGroup.inflater: LayoutInflater
    get() = LayoutInflater.from(context)

fun ViewGroup.inflateAndAttach(@LayoutRes layoutResId: Int): View = inflater.inflate(layoutResId, this, true)

fun ViewGroup.inflate(@LayoutRes layoutResId: Int): View = inflater.inflate(layoutResId, this, false)

fun ViewGroup.beginDelayedTransition(transition: Transition? = null, applier: Transition.() -> Unit = {}) {

    TransitionManager.beginDelayedTransition(this, transition?.apply(applier))
}
