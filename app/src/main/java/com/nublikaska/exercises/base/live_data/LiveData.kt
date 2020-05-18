package com.nublikaska.exercises.base.live_data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

inline fun <T, R> LiveData<T>.map(crossinline mapper: (T) -> R): LiveData<R> {

    return Transformations.map(this) { mapper(it) }
}

inline fun <T> LiveData<List<T>>.filter(crossinline predicate: (T) -> Boolean): LiveData<List<T>> {

    return Transformations.map(this) { it.filter { predicate(it) } }
}

fun <T> LiveData<T>.observe(lifecycleOwner: LifecycleOwner, onChange: (T?) -> Unit) {

    observe(lifecycleOwner, Observer { onChange(it) })
}

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, onChange: (T) -> Unit) {

    observe(lifecycleOwner) { it?.let(onChange::invoke) }
}