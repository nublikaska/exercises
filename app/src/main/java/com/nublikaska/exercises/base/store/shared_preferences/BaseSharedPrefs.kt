package com.nublikaska.exercises.base.store.shared_preferences

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import androidx.lifecycle.LiveData
import com.nublikaska.exercises.base.live_data.SharedPreferenceIntLiveData

private const val PREFS_APP_KEY = "com.nublikaska.exercises"

abstract class BaseSharedPrefs(

    context: Context,
    sharedPrefsKey: String? = PREFS_APP_KEY

) : SharedPrefs {

    protected val sharedPrefs: SharedPreferences by lazy {

        context.getSharedPreferences(
            sharedPrefsKey,
            Context.MODE_PRIVATE
        )
    }

    protected fun transaction(transform: SharedPreferences.Editor.() -> Unit) {
        val editor = sharedPrefs.edit()

        editor.transform()

        val result = editor.commit()

        if (result.not()) throw PrefsCommitFailedException("Commit transaction failed.")
    }

    override fun put(key: String, value: Int) = transaction { putInt(key, value) }

    override fun put(key: String, value: Float) = transaction { putFloat(key, value) }

    override fun put(key: String, value: Long) = transaction { putLong(key, value) }

    override fun put(key: String, value: Boolean) = transaction { putBoolean(key, value) }

    override fun put(key: String, value: String) = transaction { putString(key, value) }

    override fun put(key: String, value: Set<String>) = transaction { putStringSet(key, value) }

    override fun remove(key: String) = transaction { remove(key) }

    override fun clear() = transaction { clear() }

    override fun getInt(key: String, defaultValue: Int?): Int {

        val value = defaultValue ?: SharedPrefs.DEFAULT_INT_VALUE

        return runCatching { sharedPrefs.getInt(key, value) }.getOrDefault(value)
    }

    override fun getLiveInt(key: String, defaultValue: Int): LiveData<Int> = SharedPreferenceIntLiveData(
        this,
        key,
        defaultValue
    )

    override fun getFloat(key: String, defaultValue: Float?): Float {

        val value = defaultValue ?: SharedPrefs.DEFAULT_FLOAT_VALUE

        return runCatching { sharedPrefs.getFloat(key, value) }.getOrDefault(value)
    }


    override fun getLong(key: String, defaultValue: Long?): Long {

        val value = defaultValue ?: SharedPrefs.DEFAULT_LONG_VALUE

        return runCatching { sharedPrefs.getLong(key, value) }.getOrDefault(value)
    }

    override fun getBoolean(key: String, defaultValue: Boolean?): Boolean {

        val value = defaultValue ?: SharedPrefs.DEFAULT_BOOLEAN_VALUE

        return runCatching { sharedPrefs.getBoolean(key, value) }.getOrDefault(value)
    }

    override fun getString(key: String, defaultValue: String?): String {

        val value = defaultValue ?: SharedPrefs.DEFAULT_STRING_VALUE

        return runCatching { sharedPrefs.getString(key, value) }.getOrNull() ?: value
    }

    override fun getStringSet(key: String, defaultValue: Set<String>?): Set<String> {

        val value = defaultValue ?: setOf()

        return runCatching { sharedPrefs.getStringSet(key, value) }.getOrNull() ?: value
    }

    override fun registerOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener) {

        sharedPrefs.registerOnSharedPreferenceChangeListener(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: OnSharedPreferenceChangeListener) {

        sharedPrefs.unregisterOnSharedPreferenceChangeListener(listener)
    }
}