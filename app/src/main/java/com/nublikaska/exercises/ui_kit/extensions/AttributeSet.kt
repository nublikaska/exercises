package com.nublikaska.exercises.ui_kit.extensions

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes

fun AttributeSet.getTypedArray(context: Context,
                               @StyleableRes styleableResId: IntArray): TypedArray {

    return context.obtainStyledAttributes(this, styleableResId)
}

inline fun AttributeSet.applyStyleable(context: Context,
                                       @StyleableRes styleableResId: IntArray,
                                       action: TypedArray.() -> Unit) {

    val typedArray = getTypedArray(context, styleableResId)

    typedArray.action()

    typedArray.recycle()
}

internal inline fun <reified T> TypedArray.getEnum(@StyleableRes enumResId: Int,
                                                   defaultAttrId: Int = 0): T where T : Enum<*> {

    val enumConstants = T::class.java.enumConstants

    return enumConstants!!.find { it.ordinal == getInt(enumResId, defaultAttrId) } ?: enumConstants.first()
}