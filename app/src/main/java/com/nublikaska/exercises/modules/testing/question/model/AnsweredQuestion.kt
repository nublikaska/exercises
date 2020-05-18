package com.nublikaska.exercises.modules.testing.question.model

import android.os.Parcelable
import com.nublikaska.exercises.base.test.TestQuestion
import kotlinx.android.parcel.Parcelize

@Parcelize
class AnsweredQuestion(

    val question: TestQuestion,
    var answer: String

) : Parcelable