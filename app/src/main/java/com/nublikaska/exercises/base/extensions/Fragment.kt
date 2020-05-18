package com.nublikaska.exercises.base.extensions

import android.content.Context
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nublikaska.exercises.base.store.shared_preferences.AppSharedPrefs
import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs

@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModelCreator(

    noinline createViewModelAction: (() -> VM)? = null

) = createViewModelLazy(VM::class, { this.viewModelStore }) {

    return@createViewModelLazy when (createViewModelAction) {

        null -> defaultViewModelProviderFactory

        else -> object : ViewModelProvider.Factory {

            override fun <T : ViewModel?> create(modelClass: Class<T>): T {

                return createViewModelAction() as? T
                    ?: throw IllegalArgumentException("The requested ViewModel isn't bound")
            }
        }
    }
}

inline val Fragment.sharedPrefs: SharedPrefs
    get() = requireContext().sharedPrefs