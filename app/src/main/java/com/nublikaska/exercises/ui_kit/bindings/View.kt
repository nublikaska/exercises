package com.nublikaska.exercises.ui_kit.bindings

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.nublikaska.exercises.ui_kit.extensions.tryAnimateLayoutChanges

@BindingAdapter("visible")
fun View.setVisible(visible: Boolean?) = setVisible(visible, true)

@BindingAdapter("visible", "visibleHideWithGone", requireAll = true)
fun View.setVisible(visible: Boolean?, visibleHideWithGone: Boolean) {

    visible ?: return

    visibility = when {

        visible -> View.VISIBLE
        visibleHideWithGone -> View.GONE
        else -> View.INVISIBLE
    }
}

@BindingAdapter("visibleSmooth", "visibleHideWithGone", requireAll = false)
fun View.setVisibleSmooth(visible: Boolean?, visibleHideWithGone: Boolean = false) {

    if (visible == isVisible) return

    tryAnimateLayoutChanges().also { setVisible(visible, visibleHideWithGone) }
}