package com.nublikaska.exercises.base.live_data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T, L : LiveData<out T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))

fun <T, L : LiveData<T>> LifecycleOwner.observeNotNull(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer { it?.let(body) })