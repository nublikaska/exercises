package com.nublikaska.exercises.base.store.shared_preferences

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.lifecycle.LiveData

interface SharedPrefs {

    companion object {

        const val DEFAULT_INT_VALUE = -1
        const val DEFAULT_FLOAT_VALUE = -1f
        const val DEFAULT_LONG_VALUE = -1L
        const val DEFAULT_BOOLEAN_VALUE = false
        const val DEFAULT_STRING_VALUE = ""
    }

    fun put(key: String, value: Int)

    fun put(key: String, value: Float)

    fun put(key: String, value: Long)

    fun put(key: String, value: String)

    fun put(key: String, value: Boolean)

    fun put(key: String, value: Set<String>)

    fun getInt(key: String, defaultValue: Int? = DEFAULT_INT_VALUE): Int

    fun getLiveInt(key: String, defaultValue: Int): LiveData<Int>

    fun getFloat(key: String, defaultValue: Float? = DEFAULT_FLOAT_VALUE): Float

    fun getLong(key: String, defaultValue: Long? = DEFAULT_LONG_VALUE): Long

    fun getString(key: String, defaultValue: String? = DEFAULT_STRING_VALUE): String

    fun getBoolean(key: String, defaultValue: Boolean? = DEFAULT_BOOLEAN_VALUE): Boolean

    fun getStringSet(key: String, defaultValue: Set<String>? = setOf()): Set<String>

    fun remove(key: String)

    fun clear()

    fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener)

    fun unregisterOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener)
}