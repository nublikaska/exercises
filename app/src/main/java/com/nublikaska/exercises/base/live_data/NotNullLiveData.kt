package com.nublikaska.exercises.base.live_data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

open class NotNullLiveData<T : Any>(

    initialValue: T

) : MutableLiveData<T>(initialValue) {

    final override fun getValue(): T = super.getValue()!!
}

fun <T : Any> notNullLiveData(initialValue: T): Lazy<NotNullLiveData<T>> = lazy {

    return@lazy NotNullLiveData(initialValue)
}

fun <T : Any> LifecycleOwner.notNullLiveData(initialValue: T,
                                             onChangedAction: (T) -> Unit): Lazy<NotNullLiveData<T>> {

    val liveData = NotNullLiveData(initialValue)

    GlobalScope.launch(Dispatchers.Main) {

        liveData.observeNotNull(this@notNullLiveData, onChangedAction)
    }

    return lazyOf(liveData)
}