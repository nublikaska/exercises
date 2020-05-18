package com.nublikaska.exercises.base.extensions

import java.lang.ref.WeakReference

const val MAX_RATING = 3

fun <T> T.weak() = WeakReference(this)

fun calculateRating(correctAnswersPercent: Float) = correctAnswersPercent/100 * MAX_RATING