package com.nublikaska.exercises.base.extensions

import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import com.nublikaska.exercises.base.live_data.observeNotNull
import com.nublikaska.exercises.base.ui.BaseViewModel

inline fun <reified T : ViewModel> BaseViewModel.sharedViewModel(@IdRes navGraphId: Int): Lazy<T> = lazy {

    sharedViewModelProvider.sharedViewModelOf<T>(navGraphId)
}

fun <T> BaseViewModel.onReturnBackStackResult(onChangedAction: (T) -> Unit) {

    sharedViewModelProvider.getBackStackResultLiveData<T>()?.let {

        observeNotNull(it, onChangedAction)
    }
}

fun <T> BaseViewModel.setResultToPreviousBackStackEntry(result: T) {

    sharedViewModelProvider.setResultToPreviousBackStackEntry(result)
}