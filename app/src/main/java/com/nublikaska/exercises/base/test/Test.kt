package com.nublikaska.exercises.base.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Test(

    val id: String,
    val name: String,
    val description: String,
    val imageUrl: String? = null,
    val level: Int,
    val type: TestType,
    val passed: Boolean = false,
    val information: String?,
    val rules: List<String>,
    val questions: List<TestQuestion>,
    val correctAnswersPercent: Float = 0f

) : Parcelable