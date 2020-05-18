package com.nublikaska.exercises.base.extensions

import android.app.Activity
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener

fun KeyboardVisibilityEvent.registerEventListener(activity: Activity, callBack: (isOpen: Boolean) -> Unit) {

    registerEventListener(activity, KeyboardVisibilityEventListener(callBack))
}

fun KeyboardVisibilityEventListener(callBack: (isOpen: Boolean) -> Unit) = object : KeyboardVisibilityEventListener {

    override fun onVisibilityChanged(isOpen: Boolean) = callBack(isOpen)
}