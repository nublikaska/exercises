package com.nublikaska.exercises.base.extensions

import android.content.Context
import com.nublikaska.exercises.base.store.shared_preferences.AppSharedPrefs
import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs

inline val Context.sharedPrefs: SharedPrefs
    get() = AppSharedPrefs(this)