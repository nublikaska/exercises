package com.nublikaska.exercises.base.test

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class TestQuestion : Parcelable {

    abstract val questionText: String
    abstract val correctAnswer: String

    abstract val iconResId: Int?

    @Parcelize
    class AnswerOptionsQuestion(

        override val questionText: String,
        override val correctAnswer: String,
        override val iconResId: Int?,

        val answerOptions: List<String>

    ) : TestQuestion()

    @Parcelize
    class AnswerInputQuestion(

        override val questionText: String,
        override val correctAnswer: String,
        override val iconResId: Int?

    ) : TestQuestion()
}