package com.nublikaska.exercises.base.ui

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import java.lang.ref.WeakReference

private const val BACK_STACK_RESULT = "back_stack_result"

class SharedViewModelProvider(val fragment: WeakReference<Fragment>) {

    @PublishedApi
    internal inline fun <reified T : ViewModel> sharedViewModelOf(@IdRes navGraphId: Int): T {

        val fragment = fragment.get() ?: error("Cannot retrieve Fragment")
        val viewModel by fragment.navGraphViewModels<T>(navGraphId)

        return viewModel
    }

    internal fun <T> getBackStackResultLiveData(): LiveData<T>? = getBackStackLiveData(BACK_STACK_RESULT)

    internal fun <T> getBackStackLiveData(key: String): LiveData<T>? {

        return fragment.get()
            ?.findNavController()
            ?.currentBackStackEntry
            ?.savedStateHandle
            ?.getLiveData<T>(key)
    }

    internal fun <T> setResultToPreviousBackStackEntry(result: T) = setValueToPreviousBackStackEntry(
        result = result ,
        key = BACK_STACK_RESULT
    )

    internal fun <T> setValueToPreviousBackStackEntry(result: T, key: String) {

        fragment.get()
            ?.findNavController()
            ?.previousBackStackEntry
            ?.savedStateHandle
            ?.set(BACK_STACK_RESULT, result)
    }
}