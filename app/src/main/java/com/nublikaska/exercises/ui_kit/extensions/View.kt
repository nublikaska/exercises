package com.nublikaska.exercises.ui_kit.extensions

import android.app.Activity
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.transition.Transition
import androidx.transition.TransitionManager
import com.nublikaska.exercises.base.extensions.registerEventListener
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil.hideKeyboard

internal val View.contextAsActivity: Activity?
    get() = context as? Activity

internal fun View.hideKeyboard() = contextAsActivity?.let { hideKeyboard(it) }

internal fun View.registerKeyboardListener(callBack: (isOpen: Boolean) -> Unit) {

    contextAsActivity?.let {

        KeyboardVisibilityEvent.registerEventListener(it, callBack)
    }
}

internal fun View.getDrawable(@DrawableRes drawableResId: Int,
                              @ColorRes tintResId: Int? = null): Drawable? {

    val drawable = AppCompatResources.getDrawable(context, drawableResId)

    drawable ?: return null

    tintResId?.let {
        val color = getColor(tintResId)
        drawable.applyColor(color)
    }

    return drawable
}

internal fun View.getColor(@ColorRes colorResId: Int) = ContextCompat.getColor(context, colorResId)

internal fun View.getColorStateList(@ColorRes colorResId: Int) = ContextCompat.getColorStateList(context, colorResId)

internal fun Drawable.applyColor(@ColorInt color: Int) = apply {

    mutate()
    setColorFilter(color, PorterDuff.Mode.MULTIPLY)
}

internal fun View.setPadding(@DimenRes paddingLeftResId: Int? = null,
                             @DimenRes paddingTopResId: Int? = null,
                             @DimenRes paddingRightResId: Int? = null,
                             @DimenRes paddingBottomResId: Int? = null) = with(resources) {

    setPadding(

        paddingLeftResId?.let { getDimensionPixelSize(paddingLeftResId) } ?: paddingLeft,
        paddingTopResId?.let { getDimensionPixelSize(paddingTopResId) } ?: paddingTop,
        paddingRightResId?.let { getDimensionPixelSize(paddingRightResId) } ?: paddingRight,
        paddingBottomResId?.let { getDimensionPixelSize(paddingBottomResId) } ?: paddingBottom
    )
}

internal fun View.getDimen(@DimenRes dimenResId: Int) = context.resources.getDimension(dimenResId)

internal fun View.setupIdIfNeeded() {

    if (id == View.NO_ID) setupId()
}

internal fun View.setupId() {

    id = ViewCompat.generateViewId()
}

inline fun View.addOnFocusChangedListener(crossinline action: (hasFocus: Boolean) -> Unit) {

    val previousListener = onFocusChangeListener
    setOnFocusChangeListener { view, hasFocus ->

        previousListener?.onFocusChange(view, hasFocus)
        action.invoke(hasFocus)
    }
}

val View.parentViewGroup: ViewGroup get() = (parent as? ViewGroup)!!

fun View.tryAnimateLayoutChanges(transition: Transition? = null) {

    parentViewGroup.beginDelayedTransition(transition)
}