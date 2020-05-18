package com.nublikaska.exercises.base.live_data

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.nublikaska.exercises.base.store.shared_preferences.SharedPrefs

class SharedPreferenceIntLiveData(

    private val prefs: SharedPrefs,
    private val key: String,
    private val defaultValue: Int

) : MutableLiveData<Int>(), SharedPreferences.OnSharedPreferenceChangeListener {

    init {
        updateValue()
    }

    override fun getValue(): Int = prefs.getInt(key, defaultValue)

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {

        if (this.key == key) updateValue()
    }

    private fun updateValue() {

        value = prefs.getInt(key, defaultValue)
    }

    override fun onActive() {
        super.onActive()
        prefs.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onInactive() {
        super.onInactive()
        prefs.unregisterOnSharedPreferenceChangeListener(this)
    }
}