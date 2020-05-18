package com.nublikaska.exercises.base.resources

import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.PluralsRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import java.util.Locale

interface ResourceProvider {

    fun getString(@StringRes stringResId: Int): String

    fun getString(@StringRes stringResId: Int, vararg args: Any): String

    fun getQuantityString(@PluralsRes pluralResId: Int, quantity: Int, vararg args: Any): String

    fun getStringArray(@ArrayRes stringArrayResId: Int): List<String>

    fun getIntegerArray(@ArrayRes integerArrayResId: Int): Array<Int>

    @ColorInt
    fun getColor(@ColorRes colorResId: Int): Int

    fun getColorArray(@ArrayRes colorArrayResId: Int): Array<Int>

    fun getInteger(@IntegerRes integerResId: Int): Int

    fun getDimen(@DimenRes dimenResId: Int): Float

    fun getRawResource(@RawRes rawResId: Int): String

    fun getDrawable(@DrawableRes drawableResId: Int): Drawable

    fun getDrawableArray(@DrawableRes vararg drawableResId: Int): List<Drawable>
}

private class ResourceProviderImpl (context: Context) : ResourceProvider {

    private val context by lazy {

        val config = Configuration(context.resources.configuration)
        config.setLocale(Locale("ru"))

        context.createConfigurationContext(config)
    }

    override fun getString(stringResId: Int): String {

        return context.getString(stringResId)
    }

    override fun getString(stringResId: Int, vararg args: Any): String {

        return context.getString(stringResId, *args)
    }

    override fun getStringArray(stringArrayResId: Int): List<String> {

        return context.resources.getStringArray(stringArrayResId).toList()
    }

    override fun getIntegerArray(integerArrayResId: Int): Array<Int> {

        return context.resources.getIntArray(integerArrayResId).toTypedArray()
    }

    override fun getColor(colorResId: Int): Int {

        return ContextCompat.getColor(context, colorResId)
    }

    override fun getColorArray(colorArrayResId: Int): Array<Int> {

        return getIntegerArray(colorArrayResId)
    }

    override fun getDimen(dimenResId: Int): Float {

        return context.resources.getDimension(dimenResId)
    }

    override fun getQuantityString(pluralResId: Int, quantity: Int, vararg args: Any): String {

        return context.resources.getQuantityString(pluralResId, quantity, *args)
    }

    override fun getInteger(integerResId: Int) = context.resources.getInteger(integerResId)

    override fun getRawResource(rawResId: Int): String {

        return context.resources.openRawResource(rawResId).bufferedReader(Charsets.UTF_8).use { it.readText() }
    }

    override fun getDrawable(drawableResId: Int): Drawable = context.getDrawable(drawableResId)!!

    override fun getDrawableArray(vararg drawableResId: Int) = drawableResId.map(::getDrawable)
}